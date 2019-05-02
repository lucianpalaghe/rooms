var roomService = {
  findAllRooms(fn) {
    axios
      .get('/api/rooms')
      .then(response => fn(response))
      .catch(error => toast(error))
  },

  findRoomById(id, fn) {
    axios
      .get('/api/rooms/' + id)
      .then(response => fn(response))
      .catch(error => toast(error))
  },

  createRoom(room, fn) {
    axios
      .post('/api/rooms', room)
      .then(response => fn(response))
      .catch(error => toast(error))
  },

  updateRoom(room, fn) {
    axios
      .put('/api/rooms/' + room.id, room)
      .then(response => fn(response))
      .catch(error => toast(error))
  },

  deleteRoom(id, fn) {
    axios
      .delete('/api/rooms/' + id)
      .then(response => fn(response))
      .catch(error => toast(error))
  },
}

var equipmentService = {
  findAllEquipment(fn) {
      axios
        .get('/api/equipment')
        .then(response => fn(response))
        .catch(error => console.log(error))
    },
}

function toast(text) {
  return Vue.toasted.show(text, {
                               	 theme: "toasted-primary",
                               	 position: "top-right",
                               	 duration : 5000
                               });
}

var roomList = Vue.extend({
  template: '#room-list',
  data: function () {
    return {rooms: []};
  },
  methods:{
    deleteRoom(id, index) {
        roomService.deleteRoom(id, r => this.rooms.splice(index, 1));
    }
  },
  mounted() {
    roomService.findAllRooms(r => {this.rooms = r.data})
  }
});

var RoomView = Vue.extend({
  template: '#room-view',
  data: function () {
    return {room: []};
  },
  mounted() {
    roomService.findRoomById(this.$route.params.room_id, r => {this.room = r.data})
  }
});

var RoomAdd = Vue.extend({
  template: '#room-add',
  data() {
    return {
      room: {name: '', availableSeats: '', floor: '', equipment:[]},
      equipment: []
    }
  },
  mounted() {
      equipmentService.findAllEquipment(r => {this.equipment = r.data})
    },
  methods: {
    createRoom() {
      roomService.createRoom(this.room, r => router.push('/'))
    }
  }
});

var RoomEdit = Vue.extend({
  template: '#room-edit',
  data: function () {
    return {room: [], equipment: []};
  },
  methods: {
    updateRoom: function () {
      roomService.updateRoom(this.room, r => router.push('/'))
    }
  },
  mounted() {
      roomService.findRoomById(this.$route.params.room_id, r => {this.room = r.data}),
      equipmentService.findAllEquipment(r => {this.equipment = r.data})
    }
});

const routes= [
      		{path: '/', component: roomList},
      		{path: '/add-room', component: RoomAdd},
      		{path: '/room/:room_id', component: RoomView, name: 'room-view'},
      		{path: '/room/:room_id/edit', component: RoomEdit, name: 'room-edit'},
      	];

var router = new VueRouter({
//    mode: 'history',
	routes
});

new Vue({
  router
}).$mount('#app');
Vue.use(Toasted);

