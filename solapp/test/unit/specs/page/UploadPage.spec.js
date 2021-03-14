import {shallowMount} from '@vue/test-utils';
import UploadPage from '@/page/UploadPage';
import SolDropzone from "@/components/SolDropzone";

describe('UploadPage spec', () => {

    describe('when open page', () => {
        let wrapper = shallowMount(UploadPage, {
            sync: false,
            stubs: {
                SolDropzone: true,
            }
        });

        it('render upload-page', () => {
            expect(wrapper.find('.upload-page').exists()).toBe(true);
        });

        it('render sol dropzone', () => {
            expect(wrapper.findComponent(SolDropzone).exists()).toBe(true);
        });
    });


})
