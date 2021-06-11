<template>
  <v-app id="inspire">
    <v-navigation-drawer
      v-model="drawer"
      app
    >
      <!--  -->
      <v-list dense>
        <v-list-item router :to="{name: 'home'}">
          <!-- <v-list-item-action>
            <v-icon>home</v-icon>
          </v-list-item-action> -->
          <v-list-item-content>
            <v-list-item-title>Home</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item router :to="{name: 'smartthings'}">
          <!-- <v-list-item-action>
            <v-icon>home</v-icon>
          </v-list-item-action> -->
          <v-list-item-content>
            <v-list-item-title>Smart Things</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

      </v-list> 
    </v-navigation-drawer>

    <v-app-bar app>
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>

      <v-toolbar-title>Sol's Home</v-toolbar-title>
      <v-spacer>
      </v-spacer>
      <v-menu offset-y v-if="showWelcome">
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            color="black"
            dark
            plain
            v-bind="attrs"
            v-on="on"
          >
            <v-icon 
              dark
              dense
              flat
            >mdi-more</v-icon>
          </v-btn>
        </template>
        <v-list>
          <!-- <v-list-item>
            <v-list-item-title>My Page</v-list-item-title>
          </v-list-item> -->
          <v-list-item @click="$store.dispatch('logout')">
            <v-list-item-title >Log out</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
      <v-btn depressed plain v-else :to="{name: 'login'}">Log In</v-btn>
    </v-app-bar>
    <v-main>
      <router-view/>
      <!-- <v-progress-circular
        indeterminate
        color="primary"
      ></v-progress-circular> -->
    </v-main>
    
  </v-app>
</template>
<script>

  export default {
    data () { 
      return {
       drawer: null,
      }
    },
    computed: {
      showWelcome() {
        return this.$store.state.isLogin;
      }
    },
    method: {
    }
  }
</script>