from Classes.DiGraph import DiGraph
from Classes.GraphAlgo import GraphAlgo
from Classes.Pokemon import Pokemon
from Classes.Agent import Agent
import sys


class GameControl:


    def __init__(self,agents=None,pokemons=None):
        self.agents = {}
        self.pokemons = {}
        self.graphAlgo = GraphAlgo()
        self.algo = GraphAlgo()
        self.eps1 = 0.001
        self.eps2 = 0.000001
        self.epsilon = 0.000001

    def LengthAndPathFromPokemonToAgent(self, agent: Agent, pokemon: Pokemon) -> (float, list):
        return self.graphAlgo.shortest_path(agent.src, pokemon.src)


    def control_agent(self,pokemon: Pokemon) -> (int,float, list):
        # get pokemon and return tuple with the best length(idAgent,lengthOfThePath,listPath)
        bestAgentId=-1
        bestTuple=(sys.maxsize,[])
        tempAgentId = -1
        tempTuple =(sys.maxsize,[])

        for agent in self.agents.values():
            
            if agent.dest!=-1:
                tempAgentId=agent.id
                tempTuple=self.LengthAndPathFromPokemonToAgent(agent.id,pokemon.src)

            if bestTuple[1]>tempTuple[1]:
                bestAgentId=tempAgentId
                bestTuple=tempTuple

        return (bestAgentId,bestTuple[0],bestTuple[1])

        pass

