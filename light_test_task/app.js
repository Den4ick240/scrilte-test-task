const app = Vue.createApp({
    data() {
        return {
            showModal: false,
        }
    }
})
app.component('modal', {
    template: '#modal_template',
    data() {
        let additionalFeatures = JSON.stringify([
            {name: 'Additional feature', cost: 100},
            {name: 'Additional feature', cost: 200},
        ])
        let productOptions = [
            {name: 'Product', cost: 50, additionalFeatures: JSON.parse(additionalFeatures)},
            {name: 'Product', cost: 100, additionalFeatures: JSON.parse(additionalFeatures)},
            {name: 'Product', cost: 300, additionalFeatures: JSON.parse(additionalFeatures)}
        ]
        for (let productOption of productOptions)
            productOption.selectedFeatures = []
        return {
            productOptions,
            selectedProductOption: productOptions[0],
            comment: "",
        }
    },
    computed: {
        totalPrice() {
            const product = this.selectedProductOption
            let cost = product.cost
            if (product.selectedFeatures.length)
                cost += product.selectedFeatures
                    .map(feature => feature.cost)
                    .reduce((a, b) => a + b)
            return cost
        }
    },
})

app.component('text-field', {
    template: '#text_field',
    props: ['id', 'label', 'model', 'type', 'required']
})

app.component('custom-select', {
        template: '#custom_select',
        props: {
            optionToString: {type: Function, required: true},
            options: {type: Array, required: true},
            default: {type: String, default: null},
            tabindex: {type: Number, default: 0},
        },
        data() {
            return {
                selected: this.default
                    ? this.default
                    : this.options.length > 0
                        ? this.options[0]
                        : null,
                open: false,
            }
        },
        methods: {
            emitSelected() {
                this.$emit('input', this.selected)
            }
        },
        mounted: this.emitSelected
    }
)

app.mount('#app')