const app = Vue.createApp({
    computed: {
        usersExceptDelphine() {
            const username = 'Delphine'
            return this.users.filter(user => user.username !== username)
        }
    },
    data() {
        return {
            isButtonEnabled: true,
            users: [],
            myId: 9
        }
    },
    methods: {
        onGetUsersClicked() {
            this.isButtonEnabled = false
            const url = 'https://jsonplaceholder.typicode.com/users'
            fetch(url)
                .then(response => response.json())
                .then(data => this.users = data)
        },
        like(likedUser) {
            const {id, name, username, email, phone} = likedUser
            const user = {id, name, username, email, phone}
            const body = {
                senderId: this.myId,
                timestamp: new Date(),
                user
            }
            fetch('', {method: 'POST', body: JSON.stringify(body)})
        }
    }
})

app.mount('#app')