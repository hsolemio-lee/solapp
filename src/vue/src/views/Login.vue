<template>
    <v-container fill-height style="max-width: 450px;">
        <v-layout align-center row wrap>
            <v-flex xs12>
                <v-alert
                    :value="isShowAlert"
                    :type="isShowAlert ? getAlertType : 'error'"
                >{{getAlertText}}</v-alert>
                <v-card>
                    <v-toolbar flat >
                        <v-toolbar-title>Login</v-toolbar-title>
                    </v-toolbar>
                        <div class="pa-3">
                            <v-text-field
                            v-model="username"
                            label="Username"
                        > 
                        </v-text-field>
                        <v-text-field
                            v-model="password"
                            label="Password"
                            type="password"
                        >                  
                        </v-text-field>
                        <v-btn
                            color="primary"
                            depressed
                            large
                            block
                            @click="login({username,password})"
                        >Login</v-btn>
                    </div>
                </v-card>
            </v-flex>
        </v-layout>
    </v-container>
</template>
<script>
import { mapState, mapActions } from "vuex"; 

export default {
    data () {
        return {
            username: null,
            password: null,
        }
    },
    computed: {
        ...mapState(["isLogin", "isLoginError"]),
        getAlertType() {
            if(this.isLogin) {
                return "success";
            } else if(this.isLoginError) {
                return "error";
            }
            return "";
        },
        isShowAlert() {
            return this.isLogin || this.isLoginError;
        },
        getAlertText() {
            if(this.isLogin) {
                return "Login Success!!!!";
            } else if(this.isLoginError) {
                return "Login Fail!!!!";
            }
            return "Login Success!!!!";
        }
    },
    methods: {
        ...mapActions(["login"]),
    }
}
</script>