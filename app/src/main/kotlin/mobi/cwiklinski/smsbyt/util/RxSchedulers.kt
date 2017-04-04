package mobi.cwiklinski.smsbyt.util

import rx.Scheduler

class RxSchedulers(val mainThread: Scheduler, val background: Scheduler,
                   val queue: Scheduler, val trampoline: Scheduler)