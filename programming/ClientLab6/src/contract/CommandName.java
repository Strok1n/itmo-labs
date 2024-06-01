package contract;

public enum CommandName
{
    help,
    info,
    show,
    add,
    update,
    remove_by_id,
    clear,
  //  save,
    execute_script,
    exit,
    remove_greater,
    remove_lower,
    history,
    sum_of_tuned_in_works,
    print_ascending,
    print_field_descending_difficulty,
    register;

    @Override
    public String toString() {
        return this.name();
    }
}
