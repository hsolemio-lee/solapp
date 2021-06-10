<template>
	<div>
        <h1>Welcome to SolCha's Home</h1>
    </div>
</template>
<script>

export default {
	name: 'mainPage',
	components: {},
	props: {

	},
	data() {
		return {

		}
	},
	created() {
        this.loginCheck();
	},
	computed: {

	},
	methods: {
        loginCheck() {
            this.$http.get(`${this.$http.defaults.baseUrl}/rest/v1/login/check`)
                .then(res => {
                    console.log(res.data);
                    if(!res.data) {
                        this.$router.push({path: "/loginForm"})
                    }
                });
        }
	}
}
</script>
<style scoped>
</style>
