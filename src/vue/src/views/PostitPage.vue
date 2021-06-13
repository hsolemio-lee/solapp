<template>
    <v-container class="grey lighten-5" fill-height>
        <v-row no-gutters >
            <v-col v-for="(item, i) in items" :key="i" >
                <postit
                    :id="item.id"
                    :textCntnt="item.textCntnt"
                    class="ma-5"
                    @click.native="clickItem(item)"
                />
            </v-col>
        </v-row>
        <v-layout>
            <v-btn
                class="mx-2 floating-button"
                fab
                dark
                color="indigo"
                @click="clickPlusBtn"
            >
                <v-icon dark>
                    mdi-plus
                </v-icon>
            </v-btn>
        </v-layout>
        <v-dialog
            v-model="dialog"
            persistent
            max-width="600px"
        >
            <v-card>
                <v-card-title>
                    <span class="text-h5">Post it Editor</span>
                </v-card-title>
                <v-container>
                    <v-row>
                        <v-textarea
                            background-color="yellow"
                            class="ma-5"
                            solo
                            label="Write contents"
                            wrap="hard"
                            v-model="currentPostit.textCntnt"
                        ></v-textarea>
                    </v-row>
                </v-container>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                        color="blue darken-1"
                        text
                        @click="dialog = false"
                    >
                        Close
                    </v-btn>
                    <v-btn
                        color="blue darken-1"
                        text
                        @click="savePostit"
                    >
                        Save
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
        <v-snackbar
            bottom
            height="80"
            timeout="2000"
            v-model="snackbar"
        >
            {{ snackbarText }}
        </v-snackbar>
        <infinite-loading @infinite="infiniteHandler" spinner="waveDots"></infinite-loading>
    </v-container>
</template>
<script>
import Postit from "../components/Postit"
import {mapActions} from "vuex";
import InfiniteLoading from "vue-infinite-loading";

export default {
    
    components: {
        Postit,
        InfiniteLoading
    },
    name: 'postitPage',
    data() {
        return {
            items: null,
            dialog: false,
            currentPostit: {
                id: null,
                textCntnt: "",
            },
            snackbar: false,
            snackbarText: "",
            limit: 0,
            size: 10,
        }
    },
    created() {
        // getPostits();
    },
    methods: {
        ...mapActions(["showProgressBar"]),
        getPostits() {
            this.showProgressBar(true);
            this.$http.get(`/rest/v1/postit?page=${this.limit}&size=${this.size}`)
            .then(res => {
                console.log(res);
                this.items = res.data.content;
                this.showProgressBar(false);

            })
            .catch(error => {
                console.log(error);
                this.snackbar = true;
                this.snackbarText = "Error getting post it!!"
                this.showProgressBar(false);
            });
        },
        clickPlusBtn() {
            this.currentPostit.id = null;
            this.currentPostit.textCntnt = "",
            this.dialog = true;
        },
        savePostit() {
            this.showProgressBar(true);
            if(!this.currentPostit.id) {
                this.$http.post("/rest/v1/postit", this.currentPostit)
                .then(res => {
                    console.log(res);
                    this.showProgressBar(false);
                    this.snackbarText = "Saved Successfully!!";
                    this.snackbar = true;
                    this.dialog = false;
                    this.getPostits();
                })
                .catch(error => {
                    console.log(error);
                    this.showProgressBar(false);
                    this.snackbarText = "Failed!!";
                    this.snackbar = true;
                });
            } else {
               this.$http.put("/rest/v1/postit", this.currentPostit)
                .then(res => {
                    console.log(res);
                    this.showProgressBar(false);
                    this.snackbarText = "Saved Successfully!!";
                    this.snackbar = true;
                    this.dialog = false;
                    this.getPostits();
                })
                .catch(error => {
                    console.log(error);
                    this.showProgressBar(false);
                    this.snackbarText = "Failed!!";
                    this.snackbar = true;
                }); 
            }
        },
        clickItem(item) {
            
            this.currentPostit.id = item.id;
            this.currentPostit.textCntnt = item.textCntnt;
            this.dialog = true;
        },
        infiniteHandler($state) {
            const EACH_LEN = 30
            this.$http.get(`/rest/v1/postit?page=${this.limit}&size=${this.size}`)
            .then(res => {
                return res.data.content;
            })
            .then(data => {
                setTimeout(() => {
                    if(data.length) {
                        this.items = this.items.concat(data);
                        $state.loaded()
                        this.limit += 1
                        console.log("after", this.items.length, this.limit);
                        // 끝 지정(No more data) - 데이터가 EACH_LEN개 미만이면 
                        if(data.length / EACH_LEN < 1) {
                        $state.complete()
                        }
                    } else {
                        // 끝 지정(No more data)
                        $state.complete()
                    }
                }, 1000)
            }).catch(err => {
                console.error(err);
            });
        }
        
    }
}
</script>
<style scoped>
    .floating-button {
        position: fixed;
        bottom: 40px;
        right: 40px;
    }
</style>