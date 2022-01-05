import json
import math
import random

import settings
from Classes.Agent import Agent
from Classes.Pokemon import Pokemon
from Classes.client import Client
from run_game import Gui


def where_to_put_agents():
    taken_list = []
    node_list_graph = []
    for n in settings.graph.Nodes:
        node_list_graph.append(n)
    # if amount equals no problem :)
    if settings.agents_amount == settings.pokemons_amount:
        for i in range(settings.agents_amount):
            win_node_id = settings.pokemons[i].win_node_src.id
            settings.client.add_agent("{\"id\":" + str(win_node_id) + "}")
    # if agents are more than we need to spread the agents
    elif settings.agents_amount > settings.pokemons_amount:
        counter = 0
        for i in range(settings.pokemons_amount):
            win_node_id = settings.pokemons[i].win_node_src.id
            settings.client.add_agent("{\"id\":" + str(win_node_id) + "}")
            taken_list.append(win_node_id)
            counter = i
        for i in range(counter + 1, settings.agents_amount):
            # https://stackoverflow.com/questions/4211209/remove-all-the-elements-that-occur-in-one-list-from-another
            # https://stackoverflow.com/questions/69425514/a-random-integer-between-specific-values-excluding-0
            l3 = [x for x in node_list_graph if x not in taken_list]
            win_node_id = random.choice(l3)
            settings.client.add_agent("{\"id\":" + str(win_node_id) + "}")
    # if pokemons are more then we just use all the agents to be close to pokemons
    elif settings.agents_amount < settings.pokemons_amount:
        for i in range(settings.pokemons_amount):
            win_node_id = settings.pokemons[i].win_node_src.id
            settings.client.add_agent("{\"id\":" + str(win_node_id) + "}")


def init_connection():
    """
    init the connection to the server , and the pygame window
    """
    settings.client = Client()
    settings.client.start_connection(settings.HOST, settings.PORT)
    # login
    settings.client.log_in("207867342")
    # initializing the code info settings
    # print(settings.client.get_info())
    info = json.loads(settings.client.get_info())
    settings.agents_amount = info['GameServer']["agents"]
    # print("agents amount: " + str(settings.agents_amount))
    settings.pokemons_amount = info['GameServer']["pokemons"]
    settings.game_level = info['GameServer']["game_level"]
    settings.moves = info['GameServer']["moves"]
    settings.is_logged_in = info['GameServer']["is_logged_in"]
    settings.grade = info['GameServer']["grade"]
    settings.max_user_level = info['GameServer']["max_user_level"]
    settings.info_id = info['GameServer']["id"]
    settings.info_graph = info['GameServer']["graph"]
    json_graph = settings.client.get_graph()
    dict_graph = json.loads(json_graph)
    for n in dict_graph["Nodes"]:
        if "pos" not in n:
            x = None
            y = None
            z = None
            xyz = (x, y, z)
            node_id = int(n["id"])
            settings.graph.add_node(node_id, xyz)
        else:
            pos = n["pos"].split(",")
            xyz = (float(pos[0]), float(pos[1]), float(pos[2]))
            node_id = int(n["id"])
            settings.graph.add_node(node_id, xyz)
    for e in dict_graph["Edges"]:
        settings.graph.add_edge(int(e["src"]), int(e["dest"]), float(e["w"]))
    settings.algo.init(settings.graph)
    m = 0.0
    for edge in settings.graph.Edges.values():
        p1 = [settings.graph.Nodes.get(edge.dest).x, settings.graph.Nodes.get(edge.dest).y]
        p2 = [settings.graph.Nodes.get(edge.src).x, settings.graph.Nodes.get(edge.src).y]
        distance = math.dist(p1, p2)
        settings.distance_edges.append({"value": distance, "src": edge.src, "dest": edge.dest})
        if edge.dest > edge.src:
            # calculating the slop
            m = (settings.graph.Nodes.get(edge.dest).y - settings.graph.Nodes.get(edge.src).y) / (
                    settings.graph.Nodes.get(edge.dest).x - settings.graph.Nodes.get(edge.src).x)
            #  y = a*x + c -> c = y / a*x
            c = settings.graph.Nodes.get(edge.dest).y / m * settings.graph.Nodes.get(edge.dest).x
            yashar = {"m": m, "c": c, "src": edge.src, "dest": edge.dest}
            settings.edges_plus.append(yashar)
        else:
            # calculating the slop
            m = (settings.graph.Nodes.get(edge.dest).y - settings.graph.Nodes.get(edge.src).y) / (
                    settings.graph.Nodes.get(edge.dest).x - settings.graph.Nodes.get(edge.src).x)
            #  y = a*x + c -> c = y / a*x
            c = settings.graph.Nodes.get(edge.dest).y / m * settings.graph.Nodes.get(edge.dest).x
            yashar = {"m": m, "c": c, "src": edge.src, "dest": edge.dest}
            settings.edges_minus.append(yashar)

    json_pokemons = settings.client.get_pokemons()
    dict_pokemons = json.loads(json_pokemons)
    for pok in dict_pokemons["Pokemons"]:
        pok = pok["Pokemon"]
        pok_obj = Pokemon(value=pok["value"], type=pok["type"], pos=pok["pos"])
        pok_obj.set_edge()
        settings.pokemons.append(pok_obj)

    # the reason this func in before everything is that in order to build the agents list
    #  we need to set the agents at first to access their information
    where_to_put_agents()
    # settings.client.add_agent("{\"id\":" + str(0) + "}")
    # settings.client.add_agent("{\"id\":" + str(0) + "}")
    # settings.client.add_agent("{\"id\":" + str(0) + "}")
    json_agents = settings.client.get_agents()
    dict_agents = json.loads(json_agents)
    for agent in dict_agents['Agents']:
        agent = agent["Agent"]
        settings.agents.append(
            Agent(id=agent["id"], value=agent["value"], src=agent["src"], dest=agent["dest"], speed=agent["speed"],
                  pos=agent["pos"]))

    # settings.agents = settings.client.get_agents()
    # settings.pokemons = settings.client.get_pokemons()
    # settings.graph = settings.client.get_graph()
    # print(settings.client.get_agents())
    # print(settings.client.get_graph())
    # print(settings.client.get_pokemons())
    # print(settings.client.get_agents())
    # where_to_put_agents()


class GameControl:

    def __init__(self):
        init_connection()
        # update.start_update_process()
        Gui()
        # start the game only when we finish with gui and establishing connection

