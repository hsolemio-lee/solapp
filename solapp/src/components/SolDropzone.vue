<template>
	<div class="sol-drop-zone-main">
		<div class="dropzone-wrapper">
			<vue-dropzone v-show="showDropzone"
                          id="upload"
			              ref="dropzoneCore"
			              :class="dropzoneClass"
			              :options="config"
			              @vdropzone-success="successEvent"
			              @vdropzone-file-added="fileAddedEvent"
			              @vdropzone-total-upload-progress="totalProgressEvent"
			              @vdropzone-removed-file="removeFileEvent"
			              @vdropzone-error="dropzoneErrorEvent"
			>
				<img class="upload-image" src="@/assets/upload.png"/>
				<p>Drop the file here or click to upload</p>
				<p>You can upload only {{this.acceptedFiles}} file</p>
			</vue-dropzone>
			<div v-show="!showDropzone" class="sol-drop-zone-success">
				<p>Upload Success!!!</p>
				<md-button class="upload-another-file-btn" @click="clickUploadAnotherEvent">Upload another file.</md-button>
			</div>
		</div>
		<div>
			<md-button
				class="upload-btn"
				@click="clickUploadEvent"
				:disabled="isDisableUploadBtn"
			>Upload</md-button>
		</div>
		<sol-progress
			:status="progressbarOption.status"
			:value="progressbarOption.value"
			:report="report"
		/>
	</div>
</template>
<script>
	import vueDropzone from 'vue2-dropzone';
	import SolProgress from "./SolProgress";

	export default {
		name: 'SolDropzone',
		components: {vueDropzone, SolProgress},
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
					destroyDropzone: true,
					clickable: true,
				},
                dropzoneStatus: {
                    fileCount: 0,
	                status: ''
                },
				progressbarOption: {
					mode: 'determinate',
					value: 0,
					status: 'hide'
				},
				report: {
					insertedCount: 0,
					updatedCount: 0,
					failedCount: 0,
					totalCount: 0,
				}
			}
		},
        computed: {
		    dropzoneClass() {
		        return this.dropzoneStatus.fileCount > 0 ? 'sol-drop-zone remove-message' : 'sol-drop-zone';
            },
	        isDisableUploadBtn() {
		        return this.progressbarOption.status !== 'ready';
	        },
	        showDropzone() {
		    	return this.progressbarOption.status !== 'complete';
	        }
        },
		created() {
            this.config.maxFiles = this.maxFiles;
            this.config.acceptedFiles = this.acceptedFiles;
		},
		methods: {
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
`;
            },
			successEvent(file, res) {
				this.report.insertedCount = res.insertedCount;
				this.report.updatedCount = res.updatedCount;
				this.report.failedCount = res.failedCount;
				this.report.totalCount = res.totalCount;
				this.progressbarOption.status = 'complete';
			},
            fileAddedEvent(file) {
                if(this.$refs.dropzoneCore.dropzone.files.length > 1) {
                    this.$refs.dropzoneCore.removeFile(file);
                } else {
                    this.dropzoneStatus.fileCount++;
	                this.progressbarOption.status = 'ready';
                }
            },
            removeFileEvent() {
            	const length = this.$refs.dropzoneCore.dropzone.files.length;
            	if(length === 0) {
            		this.resetDropzone();
	            } else {
		            this.dropzoneStatus.fileCount = length;
	            }
            },
			clickUploadEvent() {
            	this.progressbarOption.status = "uploading";
				this.$refs.dropzoneCore.processQueue();
			},
			totalProgressEvent(totaluploadprogress, totalBytes, totalBytesSent) {
                this.progressbarOption.value = totaluploadprogress;

                setTimeout(()=>{
                    if(this.progressbarOption.value === 100) {
                        this.progressbarOption.status = 'saving'
                    }
                }, 1000);

			},
			resetDropzone() {
				this.$refs.dropzoneCore.removeAllFiles();
				this.dropzoneStatus.fileCount = 0;
            	this.progressbarOption.status = 'hide';
            	this.progressbarOption.value = 0;
			},
			dropzoneErrorEvent(file, message, xhr) {
            	alert(message);
            	this.$refs.dropzoneCore.removeFile(file);
			},
			clickUploadAnotherEvent() {
            	this.resetDropzone();
			}
		}
	}
</script>
<style lang="scss">

    .dropzone-wrapper {
        margin: 50px 20px 10px 20px;
        padding: 20px;
	    min-width: 600px;
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

		&-success {
			background-color: #ffffff;
			width: 100%;
			height: 100%;
			padding: 0px;
			display: flex;
			justify-content: center;
			align-items: center;
			flex-direction: column;
		}
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
    .upload-btn {
	    background-color: #ffcd00 !important;
	    width: 100px;
    }
    .upload-another-file-btn {
	    background-color: #ffcd00 !important;
	    width: 200px;
    }
</style>
