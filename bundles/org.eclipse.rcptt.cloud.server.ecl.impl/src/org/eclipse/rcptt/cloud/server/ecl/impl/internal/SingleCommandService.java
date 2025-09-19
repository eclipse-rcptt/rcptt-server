package org.eclipse.rcptt.cloud.server.ecl.impl.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.cloud.server.IServerContext;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IPipe;
import org.eclipse.rcptt.ecl.runtime.IProcess;

public abstract class SingleCommandService<T extends Command> implements ICommandService {
	private final Class<T> clazz;
	/**
	 * @param clazz - type of the command to be supported by this service
	 */
	protected SingleCommandService(Class<T> clazz) {
		this.clazz = clazz;
	}

	/**
	 * Actual implementation of command execution.
	 * @param command - a command to execute
	 * @return output, {@link java.lang.Iterable} to output one by one or a single output object 
	 */
	protected abstract Object serviceTyped(T command, IServerContext context) throws InterruptedException, CoreException;

	@Override
	public final IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {
		T typedCommand = clazz.cast(command);

		Object rv = serviceTyped(typedCommand, IServerContext.get(context));
		
		IPipe output = context.getOutput();
		if (rv instanceof Iterable) {
			for (Object item: (Iterable<?>)rv) {
				output.write(item);
			}
		} else if (rv != null) {
			output.write(rv);
		}
		return Status.OK_STATUS;
	}
}
