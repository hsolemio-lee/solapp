<template>
    <v-container fill-height style="max-width: 1200px;">
        <v-layout align-center row wrap>
            <v-card
                class="mx-auto"
                max-width="1000px"
                min-width="400px"
                v-show="userInfo"
            >
                <v-img
                    src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
                    height="200px"
                ></v-img>
                <v-card-title>
                    {{userInfo.lastName}} {{userInfo.firstName}}님 환영합니다.
                </v-card-title>
                <v-card-subtitle>
                    한솔이와 지민이의 집에 오신걸 환영합니다.
                </v-card-subtitle>
                <v-card-actions>
                <v-btn
                    color="orange lighten-2"
                    text
                >
                    주의사항
                </v-btn>

                <v-spacer></v-spacer>

                <v-btn
                    icon
                    @click="show = !show"
                >
                    <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
                </v-btn>
                </v-card-actions>
                <v-expand-transition>
                    <div v-show="show">
                        <v-divider></v-divider>
                        <v-card-text>
                            <ul>
                                <li>
                                    고양이가 귀찮은 일은 하지 말아주세요.
                                </li>
                                <li>
                                    고양이가 놀라는 일은 하지 말아주세요.
                                </li>
                                <li>
                                    고양이가 힘들어하면 나가주세요.
                                </li>
                            </ul>
                        </v-card-text>
                    </div>
                </v-expand-transition>
            </v-card>            
        </v-layout>
    </v-container>
        
</template>
<script>
import { mapState } from 'vuex';

export default {
    data() {
        return {
            show: false,
        }
    },
    computed: {
        ...mapState(["userInfo"]),
    },
    methods: {
    }
}
</script>

