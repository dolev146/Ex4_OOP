import sys

import settings
from Classes.Agent import Agent
from Classes.Pokemon import Pokemon


def LengthAndPathFromPokemonToAgent(src: int, dest: int) -> (float, list):
    return settings.algo.shortest_path(src, dest)


def control_agent(agent: Agent) -> int:
    # get Agent and return id node of the best pokemon next edge(idAgent,lengthOfThePath,listPath)
    pokemonSaveDest=-1
    bestTuple = (sys.maxsize, [])
    tempTuple = (sys.maxsize, [])
    for pokemon in list(settings.pokemons):
        tempTuple = LengthAndPathFromPokemonToAgent(agent.src, pokemon.win_node_src.id)
        if bestTuple[0] > tempTuple[0]:
            bestTuple = tempTuple
            pokemonSaveDest=pokemon.win_node_dest.id

    if len(bestTuple[1])==1:
        return pokemonSaveDest
    return bestTuple[1][1]


def make_decisions():
    """
    This function detects when the agent has reached the position he needs,
     calculates the next step and adds it to settings.move_list
     settings.move_list.append({"agent_id": agent.id , "next_node_id": next_node)
     *** note it will only add a move list when it is not exist
    """
    for agent in settings.agents:
        if agent.dest == -1:
            agent.dest = control_agent(agent)
        #    settings.movelist.append({"agent_id": agent.id, "next_node_id": agent.dest})
            settings.client.choose_next_edge(
                '{"agent_id":' + str(agent.id) + ', "next_node_id":' + str(agent.dest) + '}')
    settings.client.move()