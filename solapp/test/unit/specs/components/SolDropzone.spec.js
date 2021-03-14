import {shallowMount} from '@vue/test-utils';
import SolDropzone from '@/components/SolDropzone';
import vueDropzone from 'vue2-dropzone';
import SolProgress from '@/components/SolProgress'
import {$http} from './mockObject';
describe('SolDropzone spec', () => {



    describe('showDropzone is true', () => {
        let wrapper = shallowMount(SolDropzone, {
            sync: false,
            propsData: {
                acceptedFiles: '.csv',
                maxFiles: 1,
            },
            mocks: {
                $http,
            },
        });

        it('should render components', () => {
            expect(wrapper.findComponent(vueDropzone).exists()).toBe(true);
            expect(wrapper.findComponent(SolProgress).exists()).toBe(true);
            expect(wrapper.find('.upload-image').exists()).toBe(true);
            expect(wrapper.find('.upload-btn').exists()).toBe(true);
            expect(wrapper.find('.upload-btn').props().disabled).toBe(true);
        });

        it('progressbar status', () => {
            expect(wrapper.vm.progressbarOption.status).toBe('hide');
        });

    });

    describe('showDropzone is false', () => {
        let wrapper = shallowMount(SolDropzone, {
            sync: false,
            propsData: {
                acceptedFiles: '.csv',
                maxFiles: 1,
            },
            mocks: {
                $http,
            },
        });
        wrapper.setData({progressbarOption: {status: 'complete'}});

        it('should render components', () => {
            expect(wrapper.find('.sol-drop-zone-success').exists()).toBe(true);
            expect(wrapper.find('.upload-another-file-btn').props().disabled).toBe(false);
        });

    });

    describe('click upload button', () => {
        let dropzoneStub = {
            render: () => {},
            methods: {
                processQueue: jest.fn(),
            }
        }
        let wrapper = shallowMount(SolDropzone, {
            sync: false,
            propsData: {
                acceptedFiles: '.csv',
                maxFiles: 1,
            },
            mocks: {
                $http,
            },
            stubs: {
                vueDropzone: dropzoneStub,
            }
        });

        wrapper.vm.clickUploadEvent();

        it('after click upload button', () => {
            expect(wrapper.vm.progressbarOption.status).toBe('uploading');
        });

    });

    describe('if file is added', () => {
        let dropzoneStub = {
            render: () => {},
            methods: {
                processQueue: jest.fn(),
                removeFile: jest.fn(),
            }
        }
        let wrapper = shallowMount(SolDropzone, {
            sync: false,
            propsData: {
                acceptedFiles: '.csv',
                maxFiles: 1,
            },
            mocks: {
                $http,
            },
            stubs: {
                vueDropzone: dropzoneStub,
            }
        });
        let file = {

        }
        wrapper.vm.$refs.dropzoneCore.dropzone = {
            files: [],
        }
        wrapper.vm.fileAddedEvent(file);

        it('after file added', () => {
            expect(wrapper.vm.dropzoneStatus.fileCount).toBe(1);
            expect(wrapper.vm.progressbarOption.status).toBe('ready');
        });

    });

    describe('if file is removed', () => {
        let dropzoneStub = {
            render: () => {},
            methods: {
                processQueue: jest.fn(),
                removeFile: jest.fn(),
            }
        }
        let wrapper = shallowMount(SolDropzone, {
            sync: false,
            propsData: {
                acceptedFiles: '.csv',
                maxFiles: 1,
            },
            mocks: {
                $http,
            },
            stubs: {
                vueDropzone: dropzoneStub,
            }
        });
        let file = {

        }
        wrapper.vm.$refs.dropzoneCore.dropzone = {
            files: [{}],
        }
        wrapper.vm.removeFileEvent(file);

        it('after file removed', () => {
            expect(wrapper.vm.dropzoneStatus.fileCount).toBe(1);
        });

    });

    describe('if file is removed, file length is 0', () => {
        let dropzoneStub = {
            render: () => {},
            methods: {
                processQueue: jest.fn(),
                removeFile: jest.fn(),
                removeAllFiles: jest.fn(),
            }
        }
        let wrapper = shallowMount(SolDropzone, {
            sync: false,
            propsData: {
                acceptedFiles: '.csv',
                maxFiles: 1,
            },
            mocks: {
                $http,
            },
            stubs: {
                vueDropzone: dropzoneStub,
            }
        });
        let file = {

        }
        wrapper.vm.$refs.dropzoneCore.dropzone = {
            files: [],
        }
        const resetDropzone = jest.spyOn(wrapper.vm, 'resetDropzone').mockImplementation();
        wrapper.vm.removeFileEvent(file);
        it('after file removed', () => {
            expect(resetDropzone).toHaveBeenCalledTimes(1);
            expect(wrapper.vm.dropzoneStatus.fileCount).toBe(0);
            expect(wrapper.vm.progressbarOption.status).toBe('hide');
        });

    });

    describe('if upload success', () => {
        let wrapper = shallowMount(SolDropzone, {
            sync: false,
            propsData: {
                acceptedFiles: '.csv',
                maxFiles: 1,
            },
            mocks: {
                $http,
            },
        });
        let file = {

        }
        const res = {
            'insertedCount': 30,
            'updatedCount': 10,
            'failedCount': 10,
            'totalCount': 50,
        }
        wrapper.vm.successEvent(file, res)

        it('after file uploaded', () => {
            expect(wrapper.vm.report).toEqual(res);
            expect(wrapper.vm.progressbarOption.status).toBe('complete');
        });

    });

    describe('if upload error', () => {
        window.alert = jest.fn();
        let dropzoneStub = {
            render: () => {},
            methods: {
                processQueue: jest.fn(),
                removeFile: jest.fn(),
                removeAllFiles: jest.fn(),
            }
        }
        let wrapper = shallowMount(SolDropzone, {
            sync: false,
            propsData: {
                acceptedFiles: '.csv',
                maxFiles: 1,
            },
            mocks: {
                $http,
            },
            stubs: {
                vueDropzone: dropzoneStub
            }
        });
        let file = {

        }
        const message = "Error";
        wrapper.vm.dropzoneErrorEvent(file, message, '');

        it('after file uploaded', () => {
            expect(alert).toHaveBeenCalledWith(message);
        });

    });

    describe('if click upload another file button', () => {
        let dropzoneStub = {
            render: () => {},
            methods: {
                processQueue: jest.fn(),
                removeFile: jest.fn(),
                removeAllFiles: jest.fn(),
            }
        }
        let wrapper = shallowMount(SolDropzone, {
            sync: false,
            propsData: {
                acceptedFiles: '.csv',
                maxFiles: 1,
            },
            mocks: {
                $http,
            },
            stubs: {
                vueDropzone: dropzoneStub
            }
        });
        const resetDropzone = jest.spyOn(wrapper.vm, 'resetDropzone').mockImplementation();

        wrapper.vm.clickUploadAnotherEvent();

        it('reset dropzone', () => {
            expect(resetDropzone).toHaveBeenCalledTimes(1);
            expect(wrapper.vm.dropzoneStatus.fileCount).toBe(0);
            expect(wrapper.vm.progressbarOption.status).toBe('hide');
        });

    });

    describe('if reset dropzone called', () => {
        let dropzoneStub = {
            render: () => {},
            methods: {
                processQueue: jest.fn(),
                removeFile: jest.fn(),
                removeAllFiles: jest.fn(),
            }
        }
        let wrapper = shallowMount(SolDropzone, {
            sync: false,
            propsData: {
                acceptedFiles: '.csv',
                maxFiles: 1,
            },
            mocks: {
                $http,
            },
            stubs: {
                vueDropzone: dropzoneStub
            }
        });

        wrapper.vm.resetDropzone();

        it('after reset dropzone', () => {
            expect(wrapper.vm.dropzoneStatus.fileCount).toBe(0);
            expect(wrapper.vm.progressbarOption.status).toBe('hide');
            expect(wrapper.vm.progressbarOption.value).toBe(0);
        });

    });





})
