import json

import settings
from Classes.Agent import Agent
from Classes.DiGraph import DiGraph
from Classes.GraphAlgo import GraphAlgo
from client import Client


def where_to_put_agents():
    for i in range(settings.agents_amount):
        settings.client.add_agent("{\"id\":" + str(i) + "}")


class GameControl:

    def __init__(self):
        """
        init the connection to the server , and the pygame window
        """
        settings.client = Client()
        settings.client.start_connection(settings.HOST, settings.PORT)
        # login
        # settings.client.log_in("207867342")
        # initializing the code info settings
        print(settings.client.get_info())
        info = json.loads(settings.client.get_info())
        settings.agents_amount = info['GameServer']["agents"]
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

        # the reason this func in before everything is that in order to build the agents list
        #  we need to set the agents at first to access their information
        where_to_put_agents()
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
        print(settings.client.get_agents())
        print(settings.client.get_graph())
        print(settings.client.get_pokemons())
        print(settings.client.get_agents())
        where_to_put_agents()
        settings.client.start()

    def control_agent(self):
        """
        get the shortest path to the src of the pokimon and set that node to the pokimon , and
        """
        pass
