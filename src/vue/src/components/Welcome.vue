<template>
    <v-container>
        <v-parallax
            dark
            :src="getRandomImage"
        >
            <v-row
            align="center"
            justify="center"
            >
            <v-col
                class="text-center"
                cols="12"
            >
                <h1 class="text-h4 font-weight-thin mb-4">
                한솔이와 지민이의 집
                </h1>
                <h4 class="subheading">
                    한솔이와 지민이의 집에 오신걸 환영합니다.
                </h4>
            </v-col>
            </v-row>
        </v-parallax>
        <v-col>
            <v-card
                class="mx-auto"
                max-width="1200"
            >
                <v-list-item two-line>
                    <v-list-item-content>
                        <v-list-item-title class="text-h5">
                            연락처
                        </v-list-item-title>
                        <v-card-text>
                            이솔뿌 010-9976-1589
                            <br>
                            차지뿌 010-2778-8708
                        </v-card-text>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item>
                    <v-list-item-content>
                        <v-list-item-title class="text-h5">
                            주의사항
                        </v-list-item-title>
                        <v-card-text>
                            <ul>
                                <li>뿌뿌가 싫어하는 행동은 하지 말아주세요.</li>
                                <li>뿌뿌가 힘들어하는 행동은 하지 말아주세요.</li>
                                <li>뿌뿌가 슬퍼하는 행동은 하지 말아주세요.</li>
                                <li>뿌뿌가 화나는 행동은 하지 말아주세요.</li>
                                <li>뿌뿌가 괴로워하는 행동은 하지 말아주세요.</li>
                                <li>뿌뿌가 나가라면 나가주세요.</li>
                            </ul>
                        </v-card-text>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item>
                    <v-list-item-content>
                        <v-list-item-title class="text-h5">
                            주소
                        </v-list-item-title>
                        <v-card-text>
                            경기도 광명시 영당로 21번길 16 301호
                        </v-card-text>
                    </v-list-item-content>
                </v-list-item>
            </v-card> 
            
        </v-col>
        <v-col>
            <v-carousel
                cycle
                hide-delimiter-background
                show-arrows-on-hover
            >
                <v-carousel-item
                    v-for="(item,i) in galleries"
                    :key="i"
                    :src="item.src"
                    reverse-transition="fade-transition"
                    transition="fade-transition"
                    contain
                    max-height="800"
                ></v-carousel-item>
            </v-carousel>    
        </v-col>
        
    </v-container>
</template>
<script>
import { mapState } from 'vuex';

export default {
    data() {
        return {
            show: false,
            galleries: [
                {
                    id: 0,
                    src: require("@/assets/banner/banner-image.jpg")
                },
                {
                    id: 1,
                    src: require("@/assets/banner/gallery03.jpg")
                },
                {
                    id: 2,
                    src: require("@/assets/banner/gallery07.jpg")
                },
                {
                    id: 3,
                    src: require("@/assets/banner/gallery05.jpg")
                },
                {
                    id: 4,
                    src: require("@/assets/banner/gallery22.jpg")
                },
                {
                    id: 5,
                    src: require("@/assets/banner/ppuppu.jpg")
                }
            ]
        }
    },
    created() {
        navigator.geolocation.getCurrentPosition((position) => {
            console.log(position.coords.latitude, position.coords.longitude);
            alert(position.coords.latitude, position.coords.longitude);
        });
    },
    computed: {
        ...mapState(["userInfo"]),
        getRandomImage() {
            const id = Math.floor(Math.random() * (6 - 0)) + 0;
            return this.galleries.find(g => g.id === id).src;
        }
    },
    methods: {
        
    }
}
</script>

