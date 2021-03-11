<template>
	<div class="upload-page">
		<sol-dropzone
            ref="solDropzone"
            :acceptedFiles="'.csv'"
            :maxFiles="1"
        />
        <md-button @click="clickUploadEvent">Upload</md-button>
	</div>
</template>
<script>

import SolDropzone from "@/components/SolDropzone";

export default {
	name: 'uploadPage',
	components: {SolDropzone},
	props: {
	
	},
	data() {
		return {
		
		}
	},
	created() {
	
	},
	methods: {
        clickUploadEvent() {
            this.$refs.solDropzone.processQueue();
        }
	}
}
</script>
<style>
    .upload-page {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        width: 100%;
        height: 100%;
    }
</style>
