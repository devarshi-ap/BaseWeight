export async function apiGet<T>(path: string, signal?: AbortSignal): Promise<T> {
  const response = await fetch('/api' + path, {signal});

  if (!response.ok) {
    throw new Error(`HTTP error! Status: ${response.status}`);
  }

  return (await response.json()) as T;
}