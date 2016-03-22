package com.lib;

import android.location.Location;

public interface IAction {

	public interface Factory {

		public interface Backup {
			public abstract void callback();
		}

		public interface GPS {
			public abstract void callback(Location location);
		}

		public interface ActionObject {
			public void callback(Object object);
		}

		public interface Action {
			public void callback();
		}

	}
}
