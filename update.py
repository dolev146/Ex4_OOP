import json
from multiprocessing import Process, cpu_count
import time

import settings
from Classes.Pokemon import Pokemon


def update_pokemons():
    while True:
        json_pokemons = settings.client.get_pokemons()
        dict_pokemons = json.loads(json_pokemons)
        for pok in dict_pokemons["Pokemons"]:
            pok = pok["Pokemon"]
            settings.pokemons.append(Pokemon(value=pok["value"], type=pok["type"], pos=pok["pos"]))


def start_update_process():
    global get_client_info_process
    get_client_info_process.start()
    settings.processes.append(get_client_info_process)


def kill_process():
    global get_client_info_process
    get_client_info_process.join()


get_client_info_process = Process(target=update_pokemons)
