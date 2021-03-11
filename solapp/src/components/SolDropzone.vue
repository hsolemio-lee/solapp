<template>
	<div>
		<vue-dropzone id="upload"
		              class="sol-drop-zone"
		              :options="config"></vue-dropzone>
	</div>
</template>
<script>
	import vueDropzone from 'vue2-dropzone';
	
	export default {
		name: 'SolDropzone',
		components: {vueDropzone},
		props: {
		
		},
		data() {
			return {
				config: {
					url: `${this.$http.defaults.baseUrl}/rest/v1/users/upload"`,
					headers: {
						'Cache-Control': '',
						'X-Requested-With': '',
					}
				}
			}
		},
		created() {
		
		},
		methods: {
		
		}
	}
</script>
<style>
	.sol-drop-zone {
		margin: 20px 20px;
		background-color: #f7f7f7;
		border: none;
		min-height: 300px;
		min-width: 300px;
		padding: 0px;
	}
</style>
