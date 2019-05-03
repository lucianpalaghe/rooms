var roomService = {
  findAllRooms(func) {
    axios
      .get('/api/rooms')
      .then(response => func(response))
      .catch(error => toastError(error))
  },

  findRoomById(id, func) {
    axios
      .get('/api/rooms/' + id)
      .then(response => func(response))
      .catch(error => toastError(error))
  },

  createRoom(room, func) {
    axios
      .post('/api/rooms', room)
      .then(response => func(response))
      .catch(error => toastError(error))
  },

  updateRoom(room, func) {
    axios
      .put('/api/rooms/' + room.id, room)
      .then(response => func(response))
      .catch(error => toastError(error))
  },

  deleteRoom(id, func) {
    axios
      .delete('/api/rooms/' + id)
      .then(response => func(response))
      .catch(error => toastError(error))
  },
}

var equipmentService = {
  findAllEquipment(func) {
      axios
        .get('/api/equipment')
        .then(response => func(response))
        .catch(error => toastError(error))
    },
}

var reservationService = {
  findAllReservations(func) {
      axios
        .get('/api/reservations')
        .then(response => func(response))
        .catch(error => toastError(error))
    },

  getReservationByRoomId(id, func){
      axios
        .get('/api/reservations' + id)
        .then(response => func(response))
        .catch(error => toastError(error))
  },
}


var toastProps = { theme: "toasted-primary",
                 position: "top-right",
                 duration : 2000 }
function toastError(text) {
  return Vue.toasted.error(text, toastProps);
}

function toastSuccess(text) {
  return Vue.toasted.success(text, toastProps);
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
      roomService.createRoom(this.room, r =>{ router.push('/');
                                              toastSuccess('Created ' + this.room.name)})
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
      roomService.updateRoom(this.room, r =>{ router.push('/');
                                              toastSuccess('Updated ' + this.room.name)})
    }
  },
  mounted() {
      roomService.findRoomById(this.$route.params.room_id, r => {this.room = r.data}),
      equipmentService.findAllEquipment(r => {this.equipment = r.data})
    }
});

var ReservationAdd = Vue.extend({
  template: '#reservation-add',
  data() {
    return {
      rooms: [],
    }
  },
  mounted() {
      roomService.findAllRooms(r => {this.rooms = r.data})
    },
  methods: {
    createRoom() {
//      roomService.createRoom(this.room, r =>{ router.push('/');
//                                              toastSuccess('Created ' + this.room.name)})
    }
  }
});

const routes= [
      		{path: '/', component: roomList},
      		{path: '/add-room', component: RoomAdd},
      		{path: '/room/:room_id', components: { RoomView, ReservationView}, name: 'room-view'},
      		{path: '/room/:room_id/edit', component: RoomEdit, name: 'room-edit'},
      		{path: '/add-reservarion', component: ReservationAdd},
      	];

var router = new VueRouter({
//    mode: 'history',
	routes
});

new Vue({
  router
}).$mount('#app');
Vue.use(Toasted);

