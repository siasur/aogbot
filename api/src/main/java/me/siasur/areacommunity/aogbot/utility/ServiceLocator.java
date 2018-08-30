package me.siasur.areacommunity.aogbot.utility;

import java.util.HashMap;

public class ServiceLocator {
	
	private static ServiceLocator instance;
    private HashMap<Class<?>, Object> services;

    /**
     * Creates a new instance of the {@link ServiceLocator}
     */
    private ServiceLocator() {
        services = new HashMap<Class<?>, Object>();
    }

    /**
     * Gets the {@link ServiceLocator}
     * @return the current {@link ServiceLocator}
     */
    public static ServiceLocator getServiceLocator() {
        if (instance == null) {
            instance = new ServiceLocator();
        }

        return instance;
    }

    /**
     * Adds a service
     * @param clazz class of the defining interface
     * @param service instance of the actual implementation
     * @return <code>true</code>, if the service was added, otherwise <code>false</code>
     */
    public boolean addService(Class<?> clazz, Object service) {
        if (services.containsKey(clazz) || !clazz.isAssignableFrom(service.getClass())) {
            return false;
        }

        services.put(clazz, service);

        return true;
    }

    /**
     * Gets a service
     * @param clazz class of the defining interface
     * @param <T> defining interface
     * @return instance of the actual implementation
     */
    public <T> T getService(Class<T> clazz) {
        if (!services.containsKey(clazz)) {
            return null;
        }

        Object service = services.get(clazz);
        return clazz.cast(service);
    }

    /**
     * Removes a service
     * @param clazz class of the defining interface
     * @return <code>true</code>, if the service was removed, otherwise <code>false</code>
     */
    public boolean removeService(Class<?> clazz) {
        if (!services.containsKey(clazz)) {
            return false;
        }

        services.remove(clazz);

        return true;
    }

    /**
     * Removes all services
     */
    public void removeAllServices() {
        services.clear();
    }
	
}
