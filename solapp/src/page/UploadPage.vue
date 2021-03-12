<template>
	<div class="upload-page">
		<sol-dropzone
            ref="solDropzone"
            :acceptedFiles="acceptedFiles"
            :maxFiles="1"
        />
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
			acceptedFiles: '.csv',
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
