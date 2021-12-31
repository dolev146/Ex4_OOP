from unittest import TestCase
from Classes.CEdge import CEdge


class TestCEdge(TestCase):

    def test_constructor(self):
        edge = CEdge(dest=1, src=2, w=0.5, info="black")
        self.assertEqual(2, edge.src)
        self.assertEqual(1, edge.dest)
        self.assertEqual(0.5, edge.w)
        self.assertEqual("black", edge.info)

