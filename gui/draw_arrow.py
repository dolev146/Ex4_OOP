import math


def draw_arrow(x1, y1, x2, y2, size=12, widtha=5):
    """
    make an arrow edge.
    @param x1: node1.x
    @param x2: node2.x
    @param y1: node1.y
    @param y2: node2.y
    @param size: the size of the triangle
    @param widtha: the width of the triangle
    draw the arrow :)
    """
    y_diffrance = float(y2 - y1)
    x_diffrance = float(x2 - x1)
    distance_result = float(math.sqrt(x_diffrance * x_diffrance + y_diffrance * y_diffrance))
    minimum_x_distance = float(distance_result - size)
    node_minmimum = float(minimum_x_distance)
    dest_y_min = float(widtha)
    dest_y_max = -widtha
    divide1 = y_diffrance / distance_result
    devide2 = x_diffrance / distance_result
    trace = minimum_x_distance * devide2 - dest_y_min * divide1 + x1
    dest_y_min = minimum_x_distance * divide1 + dest_y_min * devide2 + y1
    minimum_x_distance = trace
    trace = node_minmimum * devide2 - dest_y_max * divide1 + x1
    dest_y_max = node_minmimum * divide1 + dest_y_max * devide2 + y1
    node_minmimum = trace
    return [(x2, y2), (int(minimum_x_distance), int(dest_y_min)), (int(node_minmimum), int(dest_y_max))]
