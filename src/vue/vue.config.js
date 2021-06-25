const path = require("path");
module.exports = {
  transpileDependencies: ["vuetify"],
  lintOnSave: false,
  outputDir: path.resolve(__dirname, "../main/resources/public/"),
  devServer: {
    disableHostCheck: true,
  }
};
