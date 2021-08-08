const express = require('express');
const app = express();
const http = require('http');
const server = http.createServer(app);
const IO = require('socket.io')

let client_io = IO.listen(22226)

client_io.on('connection', socket => {
    console.log('a is connect')
    socket.on('test', data => {
        console.log(data)
        client_io.emit('test1','l1 l1')
    })
})



app.get('/', (req, res) => {
    res.sendFile(__dirname + '/index.html');
});


server.listen(22225, () => {
    console.log('listening on *:22225');
});
