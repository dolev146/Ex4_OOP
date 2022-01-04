import sys

import settings
from Classes.Agent import Agent
from Classes.Pokemon import Pokemon


def LengthAndPathFromPokemonToAgent(agent: Agent, pokemon: Pokemon) -> (float, list):
    return settings.algo.shortest_path(agent.src, pokemon.win_node_src)

def control_agent(pokemon: Pokemon) -> (int, float, list):
    # get pokemon and return tuple with the best length(idAgent,lengthOfThePath,listPath)
    bestAgentId = -1
    bestTuple = (sys.maxsize, [])
    tempAgentId = -1
    tempTuple = (sys.maxsize, [])
    for agent in settings.agents.values():
        if agent.dest != -1:
            tempAgentId = agent.id
            tempTuple = LengthAndPathFromPokemonToAgent(agent.id, pokemon.win_node_src)

        if bestTuple[1] > tempTuple[1]:
            bestAgentId = tempAgentId
            bestTuple = tempTuple
    return bestAgentId, bestTuple[0], bestTuple[1]


def make_decisions():
    """
    This function detects when the agent has reached the position he needs,
     calculates the next step and adds it to settings.move_list
     settings.move_list.append({"agent_id": agent.id , "next_node_id": next_node)
     *** note it will only add a move list when it is not exist
    """
    for pokimon in settings.pokemons:


