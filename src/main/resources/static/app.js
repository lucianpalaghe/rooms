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

  findReservationById(id, func) {
    axios
      .get('/api/reservations/' + id)
      .then(response => func(response))
      .catch(error => toastError(error))
  },

  findReservationsByRoomId(id, func){
      axios
        .get('/api/reservations?roomId=' + id)
        .then(response => func(response))
        .catch(error => toastError(error))
  },

  createReservation(reservation, func) {
    axios
      .post('/api/reservations', reservation)
      .then(response => func(response))
      .catch(error => toastError(error.response.data))
  },

  updateReservation(reservation, func) {
      axios
        .put('/api/reservations/' + reservation.id, reservation)
        .then(response => func(response))
        .catch(error => toastError(error.response.data))
    },

  deleteReservation(id, func) {
      axios
        .delete('/api/reservations/' + id)
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
    deleteRoom(room, index) {
        roomService.deleteRoom(room.id, r => { this.rooms.splice(index, 1); toastSuccess('Deleted ' + room.name)});
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
      roomService.createRoom(this.room, r =>{ router.push('/');toastSuccess('Created ' + this.room.name)})
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

var ReservationListView = Vue.extend({
  template: '#reservation-list-view',
  data: function () {
    return {reservations: []};
  },
     methods:{
       deleteReservation(id, index) {
           reservationService.deleteReservation(id, r =>{ this.reservations.splice(index, 1);toastSuccess('Deleted ' + id)});
       }
     },
  mounted() {
    reservationService.findReservationsByRoomId(this.$route.params.room_id, r => {this.reservations = r.data})
  }
});

var ReservationView = Vue.extend({
  template: '#reservation-view',
  data: function () {
    return {reservation: {}};
  },
     methods:{
       deleteReservation(id, index) {
           reservationService.deleteReservation(id, r =>{ toastSuccess('Deleted ' + id)});
       }
     },
  mounted() {
    reservationService.findReservationById(this.$route.params.reservation_id, r => {this.reservation = r.data})
  }
});

var ReservationAdd = Vue.extend({
  template: '#reservation-add',
  data() {
    return {
      reservation: {roomId: '', date: new Date().toISOString().slice(0,10), from: '', to: ''},
      rooms: [],
    }
  },
  mounted() {
      roomService.findAllRooms(r => {this.rooms = r.data})
    },
  methods: {
    createReservation() {
      reservationService.createReservation(this.reservation, r =>{ router.push('/'); toastSuccess('Created reservation!')})
    }
  }
});

var ReservationEdit = Vue.extend({
  template: '#reservation-edit',
  data() {
    return {
      reservation: {},
      rooms: [],
    }
  },
  mounted() {
      reservationService.findReservationById(this.$route.params.reservation_id, r => {this.reservation = r.data}),
      roomService.findAllRooms(r => {this.rooms = r.data})
    },
  methods: {
    updateReservation: function () {
      reservationService.updateReservation(this.reservation, r =>{ router.push('/');toastSuccess('Updated ' + this.reservation.id)})
    }
  },
});

const routes= [
      		{path: '/', component: roomList},
      		{path: '/add-room', component: RoomAdd},
      		{path: '/room/:room_id', components:{ default: RoomView,
      		                                      detail: ReservationListView
      		}, props: {detail: true}, name: 'room-view'},
      		{path: '/room/:room_id/edit', component: RoomEdit, name: 'room-edit'},
      		{path: '/add-reservation', component: ReservationAdd},
      		{path: '/reservations/:reservation_id', component: ReservationView, name: 'reservation-view'},
      		{path: '/reservations/:reservation_id/edit', component: ReservationEdit, name: 'reservation-edit'},
      	];

var router = new VueRouter({
//    mode: 'history',
	routes
});

new Vue({
  router
}).$mount('#app');
Vue.use(Toasted);

