<template>
    <v-container style="max-width: 1200px;">
        <v-layout row wrap >
            <scene-card 
                v-for="(item, i) in items"
                :key="i"
                :sceneName="item.sceneName"
                :sceneId="item.sceneId"
                class="ma-10"
            />
        </v-layout>
    </v-container>
</template>
<script>

import SceneCard from "../components/SceneCard";

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
        this.$http.get("/rest/v1/smartthings/scenes")
        .then(res => {
            this.items = res.data.items;
        })
        .catch(error => {
            console.log(error);
        });
    }
}
</script>
