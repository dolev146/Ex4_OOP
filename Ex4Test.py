import math
from unittest import TestCase

import settings
from Classes.Agent import Agent
from Classes.DiGraph import DiGraph
from Classes.Pokemon import Pokemon
from agentControl import control_agent


class Ex4Test(TestCase):

    def test_pokemon_set_edge(self):
        settings.graph.add_node(1, (2, 1, 0))
        settings.graph.add_node(2, (4, 1, 0))
        settings.graph.add_edge(1, 2, 1)
        settings.graph.add_edge(2, 1, 1)
        for edge in settings.graph.Edges.values():
            p1 = [settings.graph.Nodes.get(edge.dest).x, settings.graph.Nodes.get(edge.dest).y]
            p2 = [settings.graph.Nodes.get(edge.src).x, settings.graph.Nodes.get(edge.src).y]
            distance = math.dist(p1, p2)
            settings.distance_edges.append({"value": distance, "src": edge.src, "dest": edge.dest})
        pokemon = Pokemon(5, 1, "3,1,0")
        pokemon2 = Pokemon(10, -1, "3,1,0")
        pokemon.set_edge()
        pokemon2.set_edge()
        self.assertEqual(pokemon.win_node_src.id, 5)
        self.assertEqual(pokemon2.win_node_src.id, 6)
        settings.graph = DiGraph()
        settings.distance_edges = []


    def test_make_decisions(self):

        agent=Agent(0,8,5,6,5,"2,1,0")

        settings.graph.add_node(5, (2, 1, 0))         ##
        settings.graph.add_node(6, (4, 1, 0))
        settings.graph.add_node(2, (9, 3, 0))
        settings.graph.add_edge(5, 6, 1)
        settings.graph.add_edge(6, 5, 1)
        settings.graph.add_edge(2, 5, 1)

        settings.algo.init(settings.graph)


        for edge in settings.graph.Edges.values():
            p1 = [settings.graph.Nodes.get(edge.dest).x, settings.graph.Nodes.get(edge.dest).y]
            p2 = [settings.graph.Nodes.get(edge.src).x, settings.graph.Nodes.get(edge.src).y]
            distance = math.dist(p1, p2)
            settings.distance_edges.append({"value": distance, "src": edge.src, "dest": edge.dest})
        pokemon = Pokemon(5, 1, "3,1,0")

        pokemon2 = Pokemon(10, -1, "3,1,0")
        settings.pokemons.append(pokemon)
        settings.pokemons.append(pokemon2)

        pokemon.set_edge()
        pokemon2.set_edge()
        save=control_agent(agent)
        self.assertEqual(control_agent(agent)[0],6)
        self.assertEqual(control_agent(agent)[1],pokemon)
        self.assertEqual(pokemon2.win_node_src.id, 6)
