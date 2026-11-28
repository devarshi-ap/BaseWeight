import { useEffect, useState } from 'react';
import { apiGet } from "../../api/client";

type HealthResponse = {
    status: string;
    systems: number;
};

type HealthState =
    | { kind: 'loading' }
    | { kind: 'success'; data: HealthResponse }
    | { kind: 'error'; message: string };

export function HealthPage() {
    const [state, setState] = useState<HealthState>({ kind: 'loading' });

    useEffect(() => {
        // AbortController used to cancel pending network requests and clean up event listeners (prevents race conditions)
        const controller = new AbortController();

        apiGet<HealthResponse>('/health', controller.signal)
            .then((data) => setState({ kind: 'success', data }))
            .catch((error) => {
                if (error.name === 'AbortError') return;
                console.error(error);
                setState({ kind: 'error', message: "Can't reach the server right now" });
            });

        // cleanup runs on unmount, or before the effect runs again
        return () => controller.abort();
    }, []);

    switch (state.kind) {
        case 'loading':
            return <p>Checking system health...</p>;
        case 'error':
            return <p>Error: {state.message}</p>;
        case 'success':
            return <p>Status: {state.data.status} · Systems: {state.data.systems}</p>;
    }
}