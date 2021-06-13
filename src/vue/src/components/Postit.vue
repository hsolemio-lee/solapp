<template>
    <v-card
        class="ma-5"
        color="yellow"
        dark
        min-width="100"
        min-height="100"
    >
        <v-card-text wrap="hard">
            {{textCntnt}}
        </v-card-text>
    </v-card>
</template>
<script>

export default {
    props: {
        id: {
            type: Number,
            default: null
        },
        textCntnt: {
            type: String,
            default: ""
        }
    },
    data() {
        return {
            
        }
    },
    created() {
        
    },
    computed: {
    },
    methods: {
        
    }
}
</script>

