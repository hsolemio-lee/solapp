<template>
	<div class="join-form">
        <div class="wrapper">
            <md-field>
                <label>ID</label>
                <md-input v-model="username"></md-input>
            </md-field>
            <md-field>
                <label>Password</label>
                <md-input v-model="password" type="password"></md-input>
            </md-field>
            <md-field>
                <label>email</label>
                <md-input v-model="email" type="email"></md-input>
            </md-field>
            <md-button @click="join">가입</md-button>
        </div>
    </div>
</template>
<script>

export default {
	name: 'joinPage',
	components: {},
	props: {
	
	},
	data() {
		return {
			username: '',
            password: '',
            email: '',
		}
	},
	created() {
        
	},
	computed: {
	
	},
	methods: {
        join() {
            console.log(this.username);
            console.log(this.password);
            this.$http.post(`${this.$http.defaults.baseUrl}/rest/v1/login/join`, {username: this.username, password: this.password, email: this.email})
                .then(res => {
                    this.$router.push({path:'/loginForm'});
                });
        },
	}
}
</script>
<style scoped>
.join-form {
    display: flex;
    justify-content: center;
}
.wrapper {
    min-width: 500px;
}
</style>
