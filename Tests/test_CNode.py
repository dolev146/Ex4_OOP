from unittest import TestCase

from Classes.CNode import CNode


class TestCNode(TestCase):

    def test_constructor(self):
        node = CNode(node_id_paramter=1, pos=(1, 2, 3), info="black", length=9, previous=7, rank=5)
        self.assertEqual(1, node.id)
        self.assertEqual(5, node.rank)
        self.assertEqual(7, node.previous)
        self.assertEqual("black", node.info)
        self.assertEqual(1, node.x)
        self.assertEqual(2, node.y)
        self.assertEqual(3, node.z)
