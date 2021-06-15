<template>
    <v-card
        color="#385F73"
        dark
    >
        <v-list-item three-line>
            <v-list-item-content>
                <div class="text-overline mb-4">
                    Scene
                </div>
                <v-list-item-title class="text-h5 mb-1">
                        {{scene.sceneName}}
                </v-list-item-title>
            </v-list-item-content>
        </v-list-item>
        <v-card-actions>
            <v-btn
                outlined
                rounded
                text
                :loading="buttonLoading"
                @click="executeScene"
            >
                Execute
            </v-btn>
        </v-card-actions>    
    </v-card>
</template>
<script>
import {mapActions} from "vuex"

export default {
    props: {
        sceneName: {
            type: String,
            default: ""
        },
        sceneId: {
            type: String,
            default: ""
        },
        key: {
            type: String,
            default: ""
        }
    },
    data() {
        return {
            scene : {
                sceneName: "",
                sceneId: "",
            },
            buttonLoading: false,
        }
    },
    created() {
        this.scene.sceneName = this.sceneName;
        this.scene.sceneId = this.sceneId;
    },
    computed: {
    },
    methods: {
        ...mapActions(["showSnackbar"]),
        executeScene() {
            this.buttonLoading = true;
            this.$http.post(`/rest/v1/smartthings/scenes/execute/${this.scene.sceneId}`)
            .then(res => {
                this.buttonLoading = false;
                if(res.data.status === 'success') {
                    this.showSnackbar("Execute Success!!");
                } else {
                    this.showSnackbar("Execute Fail!!");
                }
            })
            .catch(error => {
                this.buttonLoading = false;
                console.log(error);
                this.showSnackbar("Execute Fail!!");
            });
        },
    }
}
</script>

