import math
from unittest import TestCase

import settings
from Classes.DiGraph import DiGraph
from Classes.Pokemon import Pokemon


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
        self.assertEqual(pokemon.win_node_src.id, 1)
        self.assertEqual(pokemon2.win_node_src.id, 2)

    def test_make_decisions(self):
        pass