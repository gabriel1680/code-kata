import { defineConfig } from 'vitest/config'

export default defineConfig({
    test: {
        globals: true,
        testTimeout: 3000,
        include: ['*.spec.ts'],
        coverage: {
            all: true,
            provider: 'istanbul',
        },
    },
})
