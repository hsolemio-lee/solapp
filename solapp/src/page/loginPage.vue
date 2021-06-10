<template>
	<div class="login-form">
        <div class="wrapper">
            <form @submit="login()">
                <md-field>
                    <label>ID</label>
                    <md-input v-model="username"></md-input>
                </md-field>
                <md-field>
                    <label>Password</label>
                    <md-input v-model="password" type="password"></md-input>
                </md-field>
                <md-button type="submit">로그인</md-button>
                <md-button @click="goToJoinPage">회원가입</md-button>
            </form>
        </div>
    </div>
</template>
<script>

export default {
	name: 'loginPage',
	components: {},
	props: {
	
	},
	data() {
		return {
			username: '',
            password: '',
		}
	},
	created() {
        
	},
	computed: {
	
	},
	methods: {
        login() {
            this.$http.post(`${this.$http.defaults.baseUrl}/login`, {username: this.username, password: this.password})
                .then(res => {
                    console.log(res);
                })
        },
        goToJoinPage() {
            this.$router.push({path:'/joinForm'});
        }
	}
}
</script>
<style scoped>
.login-form {
    display: flex;
    justify-content: center;
}
.wrapper {
    min-width: 500px;
}
</style>
