import {shallowMount} from '@vue/test-utils';
import SolProgress from '@/components/SolProgress';

describe('SolProgress spec', () => {

    describe('when status is uploading', () => {
        let wrapper = shallowMount(SolProgress, {
            sync: false,
            propsData: {
                report: {
                    insertedCount: 10,
                    updatedCount: 10,
                    failedCount: 10,
                    totalCount: 30
                },
                status: 'uploading',
                value: 50,
            },

        });
        it('should render progress-bar', () => {
            expect(wrapper.find('.sol-progress-bar').exists()).toBe(true);
            expect(wrapper.find('.report-wrapper').exists()).toBe(false);
        });

        it('progress props should be', () => {
            expect(wrapper.find('.sol-progress-bar').props().mdMode).toBe('determinate');
            expect(wrapper.find('.sol-progress-bar').props().mdValue).toBe(50);
        });
    });

    describe('when status is saving', () => {
        let wrapper = shallowMount(SolProgress, {
            sync: false,
            propsData: {
                report: {
                    insertedCount: 30,
                    updatedCount: 15,
                    failedCount: 5,
                    totalCount: 50
                },
                status: 'saving',
                value: 100,
            }
        });

        it('should render progress-bar', () => {
            expect(wrapper.find('.sol-progress-bar').exists()).toBe(true);
            expect(wrapper.find('.report-wrapper').exists()).toBe(false);
        });

        it('progress props should be', () => {
            expect(wrapper.find('.sol-progress-bar').props().mdMode).toBe('query');
            expect(wrapper.find('.sol-progress-bar').props().mdValue).toBe(100);
        });
    });

    describe('when status is complete', () => {
        let wrapper = shallowMount(SolProgress, {
            sync: false,
            propsData: {
                report: {
                    insertedCount: 30,
                    updatedCount: 15,
                    failedCount: 5,
                    totalCount: 50
                },
                status: 'complete',
                value: 100,
            }
        });

        it('should render report', () => {
            expect(wrapper.find('.sol-progress-bar').exists()).toBe(false);
            expect(wrapper.find('.report-wrapper').exists()).toBe(true);
        });

        it('progress props should be', () => {
            expect(wrapper.find('.inserted-progress-bar').props().mdValue).toBe(60);
            expect(wrapper.find('.updated-progress-bar').props().mdValue).toBe(90);
            expect(wrapper.find('.failed-progress-bar').props().mdValue).toBe(100);
        });

        it('progress color should be', () => {
            const colorItems = wrapper.findAll('.result-indicate-item');

            expect(wrapper.find('.result-indicate-item').exists()).toBe(true);
            expect(colorItems.at(0).find('.color-chip').element.style.backgroundColor).toBe('rgb(19, 127, 210)');
            expect(colorItems.at(1).find('.color-chip').element.style.backgroundColor).toBe('rgb(13, 185, 7)');
            expect(colorItems.at(2).find('.color-chip').element.style.backgroundColor).toBe('rgb(248, 12, 12)');
        });
    });
})
