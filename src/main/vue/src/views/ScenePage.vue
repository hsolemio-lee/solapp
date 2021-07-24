<template>
    <v-container class="grey lighten-5" fill-height>
        <v-row no-gutters >
            <v-col v-for="(item, i) in items" :key="i">
                <scene-card 
                    :sceneName="item.sceneName"
                    :sceneId="item.sceneId"
                    class="ma-5"
                />
            </v-col>
        </v-row>
    </v-container>
</template>
<script>

import SceneCard from "../components/SceneCard";
import {mapActions} from "vuex";
export default {
    components: {
        SceneCard,
    },
    name: 'scenePage',
    data() {
        return {
            items: null,
        }
    },
    created() {
        this.showProgressBar(true);
        this.$http.get("/rest/v1/smartthings/scenes")
        .then(res => {
            this.showProgressBar(false);
            this.items = res.data.items;
        })
        .catch(error => {
            this.showProgressBar(false);
            console.log(error);
        });
    },
    methods: {
        ...mapActions(["showProgressBar"]),
    }
}
</script>
