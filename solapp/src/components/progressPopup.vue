<template>
	<div class="progress-wrapper" v-show="isShow">
		<md-progress-bar v-if="showProgressingBar"
			:md-mode="getMode"
			:md-value="value"
		/>
		<div v-else class="report-wrapper">
			<md-progress-bar :class="'inserted-progress-bar'"
							 :md-mode="'determinate'"
			                 :md-value="getInsertedProgressValue"
			/>
			<md-progress-bar :class="'updated-progress-bar'"
							 :md-mode="'determinate'"
			                 :md-value="getUpdatedProgressValue"
			/>
			<md-progress-bar :class="'failed-progress-bar'"
							 :md-mode="'determinate'"
			                 :md-value="getFailedProgressValue"
			/>
			<div class="result-indicate">
				<div class="result-indicate-item">
					<div class="color-chip" :style="getBackgroundColorStyle('#137fd2')"/><div>Inserted: {{report.insertedCount}}</div>
				</div>
				<div class="result-indicate-item">
					<div class="color-chip" :style="getBackgroundColorStyle('#0db907')"/><div>Updated: {{report.updatedCount}}</div>
				</div>
				<div class="result-indicate-item">
					<div class="color-chip" :style="getBackgroundColorStyle('#f80c0c')"/><div>Failed: {{report.failedCount}}</div>
				</div>
				<div>Total: {{report.totalCount}}</div>
			</div>
		</div>
		<p>{{getStatusMessage}}</p>
		<p class="progress-percent">{{getValue}} %</p>
	</div>
</template>
<script>


export default {
	name: 'progressPopup',
	props: {
		status: {
			type: [String],
			default: 'uploading'
		},
		value: {
			type: [Number, String],
			default: 0
		},
		report: {
			type: Object,
			default: {
				insertedCount: 0,
				updatedCount: 0,
				failedCount: 0,
				totalCount: 0
			}
		}
	},
	data() {
		return {
		}
	},
	computed: {
		showProgressingBar() {
			return this.status !== 'complete';
		},
		getMode() {
			if(this.status === 'uploading') {
				return 'determinate';
			} else if(this.status === 'saving') {
				return 'query';
			} else if(this.status === 'complete') {
				return 'determinate';
			}
		},
		getStatusMessage() {
			if(this.status === 'uploading') {
				return 'Uploading...';
			} else if(this.status === 'saving') {
				return 'Saving...';
			} else if(this.status === 'complete') {
				return 'Completed';
			}
			return 'Ready';
		},
		isShow() {
			return this.status === 'ready'
				|| this.status === 'uploading'
				|| this.status === 'saving'
				|| this.status === 'complete';
		},
		getValue() {
			return this.value.toFixed(2);
		},
		getInsertedProgressValue() {
			return (this.report.insertedCount / this.report.totalCount)*100;
		},
		getUpdatedProgressValue() {
			return ((this.report.insertedCount + this.report.updatedCount) / this.report.totalCount)*100;
		},
		getFailedProgressValue() {
			return 100;
		},
	},
	created() {
	},
	methods: {
		getBackgroundColorStyle(color) {
			return {backgroundColor: color};
		},
	}
}
</script>
<style lang="scss">
.progress-wrapper {
	position: relative;
	margin-top: 10px;
}
.md-progress-bar {
	height: 20px !important;
}
.progress-percent {
	position: absolute;
	top: 0;
	left: 45%;
	margin: 0;
}
.report-wrapper {
	min-width: 600px;
	position: relative;
	margin-top: 10px;
	.md-progress-bar.md-theme-default.md-determinate.inserted-progress-bar {
		z-index: 1000;
		background-color: rgba(0,0,0,0);
		.md-progress-bar-fill {
			background-color: #137fd2;
			background-blend-mode: normal;
		}
	}
	.md-progress-bar.md-theme-default.md-determinate.updated-progress-bar {
		z-index: 500;
		margin-top: -20px;
		background-color: rgba(0,0,0,0);
		.md-progress-bar-fill {
			background-color: #0db907;
			background-blend-mode: normal;
		}
	}
	.md-progress-bar.md-theme-default.md-determinate.failed-progress-bar {
		z-index: 100;
		margin-top: -20px;
		background-color: rgba(0,0,0,0);
		.md-progress-bar-fill {
			background-color: #f80c0c;
			background-blend-mode: normal;
		}
	}
	.result-indicate {
		text-align: left;
		.result-indicate-item {
			display: flex;
			align-items: center;
		}
	}
	.color-chip {
		width: 12px;
		height: 12px;
		border-radius: 50%;
		display: inline-block;
		margin-right: 10px;
	}
}

</style>
