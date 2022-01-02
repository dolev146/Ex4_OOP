from Classes.DiGraph import DiGraph
from Classes.GraphAlgo import GraphAlgo


class GameControl:
    agents = {}
    pokemons = {}
    graph = DiGraph()
    algo = GraphAlgo()
    eps1 = 0.001
    eps2 = 0.000001
    epsilon = 0.000001

    def __init__(self):
        """
        init the game
        """
        pass

    def control_agent(self):
        """
        get the shortest path to the src of the pokimon and set that node to the pokimon , and
        """
        pass

