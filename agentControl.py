import sys

import settings
from Classes.Agent import Agent
from Classes.Pokemon import Pokemon


def LengthAndPathFromPokemonToAgent(src: int, dest: int) -> (float, list):
    return settings.algo.shortest_path(src, dest)


def control_agent(agent: Agent):
    # get Agent and return id node of the best pokemon next edge(idAgent,lengthOfThePath,listPath)

    pokemonSave = None
    bestTuple = (sys.maxsize, [])
    tempTuple = (sys.maxsize, [])
    for pokemon in settings.pokemons:

        if pokemon.win_node_src.id not in settings.agentDestNodeList.values():  ## if no agent with this pokemon dest
            tempTuple = LengthAndPathFromPokemonToAgent(agent.src, pokemon.win_node_src.id)
            if bestTuple[0] > tempTuple[0] :
                bestTuple = tempTuple
                pokemonSave = pokemon

    if len(bestTuple[1]) == 1:
        return (pokemonSave.win_node_dest.id,pokemonSave)
    return (bestTuple[1][1],pokemonSave)


def make_decisions():
    """
    This function detects when the agent has reached the position he needs,
     calculates the next step and adds it to settings.move_list
     settings.move_list.append({"agent_id": agent.id , "next_node_id": next_node)
     *** note it will only add a move list when it is not exist
    """
    #listCopyPokemons=list(settings.pokemons)

    for agent in settings.agents:

        for agentForId in settings.agents:
            if agentForId.src==settings.agentDestNodeList[agentForId.id] :    # if the agent got to the pokemon src
                settings.agentDestNodeList[agentForId.id]=-1   # update to no pokemon dest

        if agent.dest == -1:

            tempTupleSave=control_agent(agent)
            agent.dest =tempTupleSave[0]
            settings.agentDestNodeList[agent.id]=agent.dest

            settings.client.choose_next_edge(
                '{"agent_id":' + str(agent.id) + ', "next_node_id":' + str(agent.dest) + '}')
    settings.client.move()
