const app = Vue.createApp({
    mounted() {
        this.showModal()
    },
    data() {
        return {
            isModalOpen: true,
        }
    },
    methods: {
        showModal() {
            this.isModalOpen = true
        },
        closeModal() {
            this.isModalOpen = false
        }
    }
})
app.component('modal', {
    template: '#modal_template',
    props: ['isModalOpen'],
    data() {
        let additionalFeatures = [
            {name: 'Additional feature', cost: 100},
            {name: 'Additional feature', cost: 200},
        ]
        let productOptions = [
            {name: 'Product', cost: 50},
            {name: 'Product', cost: 100},
            {name: 'Product', cost: 300}
        ]
        return {
            productOptions,
            selectedProductOption: null,
            additionalFeatures,
            selectedFeatures: [],
            firstName: '',
            lastName: '',
            email: '',
            comment: '',
            firstNameError: null,
            lastNameError: null,
            emailError: null,
            productOptionError: null,
        }
    },
    computed: {
        totalPrice() {
            const product = this.selectedProductOption
            let cost = product ? product.cost : 0
            if (this.selectedFeatures.length)
                cost += this.selectedFeatures
                    .map(feature => feature.cost)
                    .reduce((a, b) => a + b)
            return cost
        }
    },
    methods: {
        validateFirstName() {
            this.firstNameError = !this.firstName
                ? 'Please fill in first name.'
                : null
        },
        validateLastName() {
            this.lastNameError = !this.lastName
                ? 'Please fill in last name.'
                : null
        },
        validateEmail() {
            const emailMatchRegExr = '[a-z0-9!#$%&\'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&\'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?'
            this.emailError = !this.email
                ? 'Please fill in email.'
                : !this.email.match(emailMatchRegExr)
                    ? 'Please enter a valid email address.'
                    : null
        },
        validateProductOption() {
            this.productOptionError = !this.selectedProductOption
                ? 'Please select product type.'
                : null
        },
        submitForm(e) {
            this.validateFirstName()
            this.validateLastName()
            this.validateEmail()
            this.validateProductOption()
            if (this.productOptionError || this.emailError || this.firstNameError || this.lastNameError)
                e.preventDefault()
        },
        nextTickFocus() {
            if (this.isModalOpen)
                this.$nextTick(() =>
                    this.$refs.modal.focus()
                )
        },
        onEscapeKeyUp(event) {
            if (event.which === 27)
                this.$emit('close')
        }
    },
    watch: {
        firstName() {
            this.validateFirstName()
        },
        lastName() {
            this.validateLastName()
        },
        email() {
            this.validateEmail()
        },
        selectedProductOption() {
            this.validateProductOption()
        },
        isModalOpen: {
            immediate: true,
            handler(val) {
                if (val)
                    window.addEventListener("keyup", this.onEscapeKeyUp)
                else
                    window.removeEventListener("keyup", this.onEscapeKeyUp)
            }
        }
    },
})

app.component('text-field', {
    template: '#text_field',
    props: ['id', 'label', 'type', 'required', 'error', 'modelValue'],
    computed: {
        textModel: {
            get() {
                return this.modelValue
            },
            set(val) {
                this.$emit('update:modelValue', val)
            }
        }
    }
})

app.component('custom-select', {
        template: '#custom_select',
        props: {
            optionToString: {type: Function, required: true},
            options: {type: Array, required: true},
            default: {type: String, default: null},
            tabindex: {type: Number, default: 0},
            error: {type: String, default: null},
            name: {type: String, default: null},
        },
        data() {
            return {
                selected: this.default,
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