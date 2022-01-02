"""
@author AchiyaZigi
OOP - Ex4
Very simple GUI example for python client to communicates with the server and "play the game!"
"""
import json
from operator import attrgetter

import settings
from pygame import gfxdraw
import pygame
from pygame import *

import time

from Classes.Agent import Agent
from Classes.Pokemon import Pokemon


class Gui:
    def __init__(self):
        start = time.time()

        # init pygame
        WIDTH, HEIGHT = 1080, 720
        pygame.init()
        screen = display.set_mode((WIDTH, HEIGHT), depth=32, flags=RESIZABLE)
        clock = pygame.time.Clock()
        pygame.font.init()

        # client = Client()
        # client.start_connection(HOST, PORT)

        # pokemons = client.get_pokemons()
        # pokemons_obj = json.loads(pokemons, object_hook=lambda d: SimpleNamespace(**d))

        # pokemons_obj = json.loads(pokemons, object_hook=lambda d: SimpleNamespace(**d))
        # print(pokemons)

        # graph_json = client.get_graph()
        # graph_json = client.get_graph()

        FONT = pygame.font.SysFont('Arial', 20, bold=True)
        # load the json string into SimpleNamespace Object

        # graph = json.loads(
        #     graph_json, object_hook=lambda json_dict: SimpleNamespace(**json_dict))

        # for n in graph.Nodes:
        #     x, y, _ = n.pos.split(',')
        #     n.pos = SimpleNamespace(x=float(x), y=float(y))

        # get data_ex3 proportions
        node_list = list(settings.graph.Nodes.values())
        min_x = min(node_list, key=lambda node: node.x)
        min_y = min(node_list, key=lambda node: node.y)
        max_x = max(node_list, key=lambda node: node.x)
        max_y = max(node_list, key=lambda node: node.y)

        def scale(data, min_screen, max_screen, min_data, max_data):
            """
            get the scaled data_ex3 with proportions min_data, max_data
            relative to min and max screen dimentions
            """
            return ((data - min_data) / (max_data - min_data)) * (max_screen - min_screen) + min_screen

        # decorate scale with the correct values

        def my_scale(data, x=False, y=False):
            if x:
                return scale(data, 50, screen.get_width() - 50, min_x.x, max_x.x)
            if y:
                return scale(data, 50, screen.get_height() - 50, min_y.y, max_y.y)

        radius = 15

        # need to check how much agents i have
        # client.add_agent("{\"id\":0}")
        # client.add_agent("{\"id\":1}")
        # client.add_agent("{\"id\":2}")
        # client.add_agent("{\"id\":3}")

        # this commnad starts the server - the game is running now

        """
        The code below should be improved significantly:
        The GUI and the "algo" are mixed - refactoring using MVC design pattern is required.
        """
        # counter = 0
        settings.client.start()
        while settings.client.is_running() == 'true':
            # pokemons = [p.Pokemon for p in settings.pokemons]
            # for p in pokemons:
            #     x, y, _ = p.pos.split(',')
            #     p.pos = SimpleNamespace(x=my_scale(
            #         float(x), x=True), y=my_scale(float(y), y=True))
            # agents = json.loads(settings.client.get_agents(),
            #                     object_hook=lambda d: SimpleNamespace(**d)).Agents
            # agents = [agent.Agent for agent in agents]
            # for a in agents:
            #     x, y, _ = a.pos.split(',')
            #     a.pos = SimpleNamespace(x=my_scale(
            #         float(x), x=True), y=my_scale(float(y), y=True))
            json_pokemons = settings.client.get_pokemons()
            dict_pokemons = json.loads(json_pokemons)
            for pok in dict_pokemons["Pokemons"]:
                pok = pok["Pokemon"]
                settings.pokemons.append(Pokemon(value=pok["value"], type=pok["type"], pos=pok["pos"]))

            json_agents = settings.client.get_agents()
            dict_agents = json.loads(json_agents)
            for agent in dict_agents['Agents']:
                agent = agent["Agent"]
                settings.agents.append(
                    Agent(id=agent["id"], value=agent["value"], src=agent["src"], dest=agent["dest"],
                          speed=agent["speed"],
                          pos=agent["pos"]))

            # check events
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    settings.client.stop_connection()
                    # settings.processes[0].join()
                    pygame.quit()
                    exit(0)

            # refresh surface
            screen.fill(Color(0, 0, 0))

            # draw nodes
            for n in settings.graph.Nodes.values():
                x = my_scale(n.x, x=True)
                y = my_scale(n.y, y=True)
                # its just to get a nice antialiasing circle
                gfxdraw.filled_circle(screen, int(x), int(y),
                                      radius, Color(64, 80, 174))
                gfxdraw.aacircle(screen, int(x), int(y),
                                 radius, Color(255, 255, 255))
                # draw the node id
                id_srf = FONT.render(str(n.id), True, Color(255, 255, 255))
                rect = id_srf.get_rect(center=(x, y))
                screen.blit(id_srf, rect)

            # draw edges
            for e in settings.graph.Edges.values():
                # find the edge nodes
                src = next(n for n in settings.graph.Nodes.values() if n.id== e.src)
                dest = next(n for n in settings.graph.Nodes.values() if n.id == e.dest)
                # scaled positions
                src_x = my_scale(src.x, x=True)
                src_y = my_scale(src.y, y=True)
                dest_x = my_scale(dest.x, x=True)
                dest_y = my_scale(dest.y, y=True)
                # draw the line
                pygame.draw.line(screen, Color(61, 72, 126),
                                 (src_x, src_y), (dest_x, dest_y))

            # draw agents
            for agent in settings.agents:
                x = my_scale(agent.x, x=True)
                y = my_scale(agent.y, y=True)
                pygame.draw.circle(screen, Color(122, 61, 23),
                                   (int(x), int(y)), 10)
            # draw pokemons (note: should differ (GUI wise) between the up and the down pokemons
            # (currently they are marked in the same way).
            for p in settings.pokemons:
                x = my_scale(p.x, x=True)
                y = my_scale(p.y, y=True)
                pygame.draw.circle(screen, Color(0, 255, 255), (int(x), int(y)), 10)

            # update screen changes
            display.update()

            # refresh rate
            clock.tick(60)

            json_pokemons = settings.client.get_pokemons()
            dict_pokemons = json.loads(json_pokemons)
            for pok in dict_pokemons["Pokemons"]:
                pok = pok["Pokemon"]
                settings.pokemons.append(Pokemon(value=pok["value"], type=pok["type"], pos=pok["pos"]))


            # choose next edge
            # for agent in settings.agents:
            #     if agent.dest == -1:
            #         next_node = (agent.src - 1) % len(settings.graph.Nodes)
            #         settings.client.choose_next_edge(
            #             '{"agent_id":' + str(agent.id) + ', "next_node_id":' + str(next_node) + '}')
            #         ttl = settings.client.time_to_end()
            #         print(ttl, settings.client.get_info())

            # if ((time.time() - start) == 10.0):
            # print("Process time: " + str(time.time() - start))

            # print(counter)
            # counter = counter + 1

            # settings.client.move()
        # game over:
