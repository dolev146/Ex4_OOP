import settings


def decide_to_move():
    """
        This function manages the sending of the client.move() function
        according to the information obtained from settings.move_list
        In addition it should take into account the elapsed time so that it
        does not send over 10 times a move per second
    """
    if len(settings.movelist) > 0:
        for move in list(settings.movelist):
            settings.client.choose_next_edge(
                '{"agent_id":' + str(move["agent_id"]) + ', "next_node_id":' + str(move["next_node_id"]) + '}')
            settings.movelist.remove(move)
        settings.client.move()
