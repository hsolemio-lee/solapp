<template>
	<div class="dropzone-wrapper">
		<vue-dropzone id="upload"
                      ref="dropzoneCore"
		              :class="dropzoneClass"
		              :options="config"
                      @vdropzone-complete="successEvent"
                      @vdropzone-file-added="fileAddedEvent"
                      @vdropzone-total-upload-progress="()=>{}"
                      @vdropzone-removed-file="removeFileEvent"
        >
            <img class="upload-image" src="@/assets/upload.png"/>
            <p>Drop the file here or click to upload</p>
            <p>You can upload only {{this.acceptedFiles}} file</p>
        </vue-dropzone>
	</div>
</template>
<script>
	import vueDropzone from 'vue2-dropzone';

	export default {
		name: 'SolDropzone',
		components: {vueDropzone},
		props: {
            acceptedFiles: {
                type: [String, Array],
                default : '.csv'
            },
            maxFiles: {
                type: [String, Number],
                default: ''
            }
		},
		data() {
			return {
				config: {
					url: `${this.$http.defaults.baseUrl}/rest/v1/users/upload`,
					headers: {
						'Cache-Control': '',
						'X-Requested-With': '',
					},
                    maxFiles: 1,
                    addRemoveLinks: true,
                    acceptedFiles: '.csv',
                    previewTemplate: this.fileTemplate(),
                    autoProcessQueue: false,

				},
                dropzoneStatus: {
                    fileCount: 0,
                }
			}
		},
        computed: {
		    dropzoneClass() {
		        return this.dropzoneStatus.fileCount > 0 ? 'sol-drop-zone remove-message' : 'sol-drop-zone';
            }
        },
		created() {
            this.config.maxFiles = this.maxFiles;
            this.config.acceptedFiles = this.acceptedFiles;
		},
		methods: {
            successEvent() {

            },
            fileTemplate() {
                return `<div class="dz-preview dz-file-preview sol-dropzone-preview">
                          <div class="dz-image">
                            <div data-dz-thumbnail-bg></div>
                          </div>
                          <div class="dz-details">
                            <div class="dz-size"><span data-dz-size></span></div>
                            <div class="dz-filename"><span data-dz-name></span></div>
                          </div>
                          <div class="dz-progress"><span class="dz-upload" data-dz-uploadprogress></span></div>
                          <div class="dz-error-message"><span data-dz-errormessage></span></div>
                          <div class="dz-success-mark"><i class="fa fa-check"></i></div>
                          <div class="dz-error-mark"><i class="fa fa-close"></i></div>
                        </div>
                        <input type="button" >Upload</input>
`;
            },
            fileAddedEvent(file) {

                if(this.$refs.dropzoneCore.dropzone.files.length > 1) {
                    alert("Max File Count exceed!!!");
                    this.$refs.dropzoneCore.removeFile(file);
                } else {
                    this.dropzoneStatus.fileCount++;
                }
            },
            removeFileEvent() {
                this.dropzoneStatus.fileCount = this.$refs.dropzoneCore.dropzone.files.length;
            }
		}
	}
</script>
<style lang="scss">

    .dropzone-wrapper {
        margin: 50px;
        padding: 20px;
        width: 350px;
        height: 350px;
        background-color: #ffcd00;
        border-radius: 10px;
    }

	.sol-drop-zone {
		background-color: #ffffff;
		width: 100%;
        height: 100%;
		padding: 0px;
        border-radius: 10px;
        display: flex;
        justify-content: center;
    }
    .sol-drop-zone.remove-message  {
        .dz-message {
            display: none;
        }
    }
    .dz-message {
        margin: auto;
    }
    .upload-image {
        width: 100px;
        height: 100px;
    }
    .sol-dropzone-preview {
        margin: auto;
    }
</style>
