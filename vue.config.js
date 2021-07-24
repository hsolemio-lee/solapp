const path = require("path");
module.exports = {
    transpileDependencies: ["vuetify"],
    outputDir: path.resolve(__dirname, "src/main/resources/public/"),
    configureWebpack: {
        resolve: {
            alias: {
                '@': path.join(__dirname, 'src/main/vue/src')
            }
        }
    },
    pages: {
      index: {
        // entry for the page
        entry: 'src/main/vue/src/main.js',
        filename: 'index.html',
        title: 'Sol App',
        // chunks to include on this page, by default includes
        // extracted common chunks and vendor chunks.
        chunks: ['chunk-vendors', 'chunk-common', 'index']
      },
    }
};