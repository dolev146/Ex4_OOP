"""
@author AchiyaZigi
OOP - Ex4
Very simple GUI example for python client to communicates with the server and "play the game!"
"""
# from time import time
import json
from operator import attrgetter

import settings
from pygame import gfxdraw
import pygame
from pygame import *

import time

from Classes.Agent import Agent
from Classes.Pokemon import Pokemon
from agentControl import make_decisions
from gui.Button import Button


class Gui:
    def __init__(self):
        start = time.time()
        agent_png = pygame.image.load("agent.png")
        pika_png = pygame.image.load("pika.png")
        eve_png = pygame.image.load("eve.png")
        bg_png = pygame.image.load("bg.png")

        # init pygame
        WIDTH, HEIGHT = 1080, 720
        pygame.init()
        screen = display.set_mode((WIDTH, HEIGHT), depth=32, flags=RESIZABLE)
        display.set_caption('Ex4 Pokemon Ariel by Dolev , Daniel and Yakov')
        icon_game = pygame.image.load("icon.png")
        pygame.display.set_icon(icon_game)
        clock = pygame.time.Clock()
        pygame.font.init()

        FONT = pygame.font.SysFont('Arial', 20, bold=True)

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

        """
        The code below should be improved significantly:
        The GUI and the "algo" are mixed - refactoring using MVC design pattern is required.
        """
        # counter = 0
        center_button = Button((137, 122, 204), 2, 2, 70, 20, 'stop')

        base_font_timer = pygame.font.Font(None, 20)
        base_font_move_counter = pygame.font.Font(None, 20)
        base_font_overall_points = pygame.font.Font(None, 20)
        base_font_pokemon_value = pygame.font.Font(None, 20)
        base_font_pokemon_type = pygame.font.Font(None, 20)

        settings.client.start()
        length_game_time = float(settings.client.time_to_end()) * 0.001
        stop_button_pressed = False
        while stop_button_pressed is not True and settings.client.is_running() == 'true':
            info_from_server = settings.client.get_info()
            info_from_server = json.loads(info_from_server)
            move_counter = info_from_server['GameServer']["moves"]
            overall_points = info_from_server['GameServer']["grade"]
            time_to_end = float(settings.client.time_to_end()) * 0.001
            if time_to_end < 100 * 0.001:
                print(settings.client.get_info())
                settings.client.stop()
                # settings.client.stop_connection()
                pygame.quit()
                exit(0)
            # check events
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    print(settings.client.get_info())
                    settings.client.stop()
                    pygame.quit()
                    exit(0)
                elif event.type == pygame.MOUSEBUTTONDOWN:
                    if center_button.isOver(pygame.mouse.get_pos()):
                        print(settings.client.get_info())
                        stop_button_pressed = True
                        settings.client.stop()
                        # settings.client.stop_connection()
                        pygame.quit()
                        # exit(0)

            if stop_button_pressed is not True:
                time_to_end_float = time_to_end
                time_to_end = f"time to end: {round(time_to_end, 2)}"

                settings.pokemons.clear()
                json_pokemons = settings.client.get_pokemons()
                dict_pokemons = json.loads(json_pokemons)
                for pok in dict_pokemons["Pokemons"]:
                    pok = pok["Pokemon"]
                    pok_obj = Pokemon(value=pok["value"], type=pok["type"], pos=pok["pos"])
                    pok_obj.set_edge()
                    settings.pokemons.append(pok_obj)

                settings.agents.clear()
                json_agents = settings.client.get_agents()
                dict_agents = json.loads(json_agents)
                for agent in dict_agents['Agents']:
                    agent = agent["Agent"]
                    settings.agents.append(
                        Agent(id=agent["id"], value=agent["value"], src=agent["src"], dest=agent["dest"],
                              speed=agent["speed"], pos=agent["pos"]))
                #   if agentInRadius()

                # refresh surface
                screen.fill(Color(168, 228, 160))
                screen.blit(pika_png, (150, 2))
                screen.blit(eve_png, (150, 32))
                print_type_plus = base_font_pokemon_type.render(str("type=1"), True, (0, 0, 128))
                print_type_minus = base_font_pokemon_type.render(str("type=-1"), True, (0, 0, 128))
                screen.blit(print_type_plus,(190,15))
                screen.blit(print_type_minus,(190,42))

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
                    src = next(n for n in settings.graph.Nodes.values() if n.id == e.src)
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
                    screen.blit(agent_png, (int(x) - 15, int(y) - 15))
                    # pygame.draw.circle(screen, Color(122, 61, 23),
                    #                    (int(x), int(y)), 10)

                # draw pokemons (note: should differ (GUI wise) between the up and the down pokemons
                # (currently they are marked in the same way).
                for p in settings.pokemons:
                    x = my_scale(p.x, x=True)
                    y = my_scale(p.y, y=True)
                    if p.type > 0:
                        screen.blit(pika_png, (int(x) - 15, int(y) - 15))
                        value_pokemon_to_print = base_font_pokemon_value.render(str(p.value), True, (0, 0, 128))
                        screen.blit(value_pokemon_to_print, (int(x) - 10, int(y) + 20))
                        # pygame.draw.circle(screen, Color(252, 3, 23), (int(x), int(y)), 10)
                    else:
                        screen.blit(eve_png, (int(x) - 15, int(y) - 15))
                        value_pokemon_to_print = base_font_pokemon_value.render(str(p.value), True, (0, 0, 128))
                        screen.blit(value_pokemon_to_print, (int(x) - 10, int(y) + 20))
                        # pygame.draw.circle(screen, Color(0, 255, 255), (int(x), int(y)), 10)

                center_button.draw(screen)

                text_timer_to_print = base_font_timer.render(time_to_end, True, (0, 0, 128))
                screen.blit(text_timer_to_print, (2, 30))
                move_counter_to_print = base_font_move_counter.render(str(f"move counter : {move_counter}"), True,
                                                                      (0, 0, 128))
                screen.blit(move_counter_to_print, (2, 50))
                overall_points_to_print = base_font_overall_points.render(str(f"grade : {overall_points}"), True,
                                                                          (0, 0, 128))
                screen.blit(overall_points_to_print, (2, 70))
                xy_tuple = screen.get_rect().bottomright
                screen.blit(bg_png, (xy_tuple[0] - 300, xy_tuple[1] - 120))

                # update screen changes
                display.update()
                clock.tick(60)

                if (length_game_time - time_to_end_float) * 10 > move_counter:
                    make_decisions()
