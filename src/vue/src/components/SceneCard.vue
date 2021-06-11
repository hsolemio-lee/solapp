<template>
    <v-card
        class="mx-auto"
        width="344"
        outlined
        :key="scene.sceneId"
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
                @click="executeScene"
            >
                Execute
            </v-btn>
        </v-card-actions>
    </v-card>
</template>
<script>

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
            }
        }
    },
    created() {
        this.scene.sceneName = this.sceneName;
        this.scene.sceneId = this.sceneId;
    },
    computed: {
    },
    methods: {
        executeScene() {
            this.$http.post(`/rest/v1/smartthings/scenes/execute/${this.scene.sceneId}`)
            .then(res => {
                if(res.data.status === 'success') {
                    alert("Execute success!");
                } else {
                    alert("Execute fail!");
                }
            })
            .catch(error => {
                console.log(error);
                alert("Execute fail!");
            });
        }
    }
}
</script>

