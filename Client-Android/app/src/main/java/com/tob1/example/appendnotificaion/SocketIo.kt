package com.tob1.example.appendnotificaion

import io.socket.client.IO
import io.socket.client.Socket


class SocketIo {

    companion object{
        @Volatile
        private var SOCKET : Socket? = null

        fun getSocket() : Socket {
            return SOCKET ?: synchronized(this){
                val socket = IO.socket("http://192.168.1.123:22226")
                SOCKET = socket
                socket
            }
        }
    }





}
